package mihaic.com.example.house_tasks_admin.data;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.function.Consumer;

import mihaic.com.example.house_tasks_admin.data.model.LoggedInUser;
import mihaic.com.example.house_tasks_admin.services.AdminService;
import mihaic.com.example.house_tasks_admin.services.LoginRequest;
import mihaic.com.example.house_tasks_admin.services.Token;
import mihaic.com.example.house_tasks_admin.services.UserRequest;
import mihaic.com.example.house_tasks_admin.ui.register.User;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class UserRepository {

    private static volatile UserRepository instance;

    private AdminService dataSource;
    private TokenPersister tokenPersister;
    private Token token;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private UserRepository(AdminService dataSource, TokenPersister tokenPersister) {
        this.dataSource = dataSource;
        this.tokenPersister = tokenPersister;
    }

    public static UserRepository getInstance(AdminService dataSource, TokenPersister tokenPersister) {
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

    public void login(String username, String password, Consumer<Result> callback) {
        // handle login
        new LoginTask(username, password, callback).execute();
    }

    public void register(UserRequest userRequest, Consumer<Result> callback) {
        new RegisterTask(userRequest, callback).execute();

    }

    class LoginTask extends AsyncTask<Void, Void, Result> {
        String password;
        String username;
        Consumer<Result> callback;

        public LoginTask(String username, String password, Consumer<Result> callback) {
            this.password = password;
            this.username = username;
            this.callback = callback;
        }

        @Override
        protected Result doInBackground(Void[] params) {
            try {
                Token token = dataSource.loginUser(new LoginRequest(username, password)).execute().body();
                return new Result.Success(token);
            } catch (Exception e) {
                return new Result.Error(e);
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result instanceof Result.Success) {
                saveToken(((Token) ((Result.Success) result).getData()));
            }
            callback.accept(result);
        }
    }

    class RegisterTask extends AsyncTask<Void, Void, Result> {

        private UserRequest userRequest;
        private Consumer<Result> callback;
        private Token token;

        public RegisterTask(UserRequest userRequest, Consumer<Result> callback) {
            this.userRequest = userRequest;
            this.callback = callback;
        }

        @Override
        protected Result doInBackground(Void[] params) {
            try {
                User user = dataSource.registerUser(userRequest).execute().body();

                LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());
                token = dataSource.loginUser(loginRequest).execute().body();

                return new Result.Success(user);
            } catch (IOException e) {
                return new Result.Error(e);
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            if (token != null) {
                saveToken(token);
            }
            callback.accept(result);
        }
    }

}


