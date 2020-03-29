package mihaic.com.example.house_tasks_admin.data.users;

import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mihaic.com.example.house_tasks_admin.data.Result;
import mihaic.com.example.house_tasks_admin.data.Token;
import mihaic.com.example.house_tasks_admin.network.users.dto.LoginRequest;
import mihaic.com.example.house_tasks_admin.network.users.dto.UserRequest;
import mihaic.com.example.house_tasks_admin.services.TokenStoreService;
import mihaic.com.example.house_tasks_admin.services.UserService;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

@Singleton
public class UserRepository {

    private UserService dataSource;
    private TokenStoreService tokenStoreService;
    private Token token;

    @Inject
    public UserRepository(UserService dataSource, TokenStoreService tokenStoreService) {
        this.dataSource = dataSource;
        this.tokenStoreService = tokenStoreService;
    }

    public boolean isLoggedIn() {
        return tokenStoreService.getToken() != null;
    }

    public Token getToken() {
        if (token == null) {
            this.token = tokenStoreService.getToken();
        }
        return this.token;
    }

    private void saveToken(Token token) {
        this.token = token;
        tokenStoreService.save(token);
    }

    public void logout() {
        token = null;
        tokenStoreService.save(new Token());
    }

    public Disposable login(String username, String password, Consumer<Result> callback) {
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
        Disposable disposable = Observable.merge(
                dataSource.registerUser(userRequest).toObservable(),
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


