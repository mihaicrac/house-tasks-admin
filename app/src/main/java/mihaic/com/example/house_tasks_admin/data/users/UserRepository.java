package mihaic.com.example.house_tasks_admin.data.users;

import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mihaic.com.example.house_tasks_admin.data.Result;
import mihaic.com.example.house_tasks_admin.data.Token;
import mihaic.com.example.house_tasks_admin.data.TokenPersister;
import mihaic.com.example.house_tasks_admin.network.users.LoginRequest;
import mihaic.com.example.house_tasks_admin.network.users.UserRequest;
import mihaic.com.example.house_tasks_admin.services.UserService;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class UserRepository {

    private static volatile UserRepository instance;

    private UserService dataSource;
    private TokenPersister tokenPersister;
    private Token token;

    // private constructor : singleton access
    private UserRepository(UserService dataSource, TokenPersister tokenPersister) {
        this.dataSource = dataSource;
        this.tokenPersister = tokenPersister;
    }

    public static UserRepository getInstance(UserService dataSource, TokenPersister tokenPersister) {
        if (instance == null) {
            instance = new UserRepository(dataSource, tokenPersister);
        }

        return instance;
    }

    public boolean isLoggedIn() {
        return tokenPersister.getToken() != null;
    }

    public Token getToken() {
        if (token == null) {
            this.token = tokenPersister.getToken();
        }
        return this.token;
    }

    private void saveToken(Token token) {
        this.token = token;
        tokenPersister.save(token);
    }

    public void logout() {
        token = null;
        tokenPersister.save(new Token());
    }

    public Disposable login(String username, String password, Consumer<Result> callback) {
        // handle login
        Disposable disposable = dataSource.loginUser(new LoginRequest(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            saveToken(result);
                            callback.accept(new Result.Success(result));
                        }
                );
        return disposable;
    }

    public Disposable register(UserRequest userRequest, Consumer<Result> callback) {
        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());
        Disposable disposable = Observable.merge(dataSource.registerUser(userRequest).toObservable(),
                dataSource.loginUser(loginRequest).toObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(result -> {
                    if (result instanceof Token) {
                        saveToken((Token) result);
                    } else {
                        callback.accept(new Result.Success(result));
                    }
                });
        return disposable;
    }

}


