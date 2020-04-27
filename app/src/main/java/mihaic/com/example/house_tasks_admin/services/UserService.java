package mihaic.com.example.house_tasks_admin.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.data.Token;
import mihaic.com.example.house_tasks_admin.network.users.dto.LoginRequest;
import mihaic.com.example.house_tasks_admin.network.users.UserClient;
import mihaic.com.example.house_tasks_admin.network.users.dto.UserRequest;
import mihaic.com.example.house_tasks_admin.data.users.User;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Singleton
public class UserService {

    private UserClient userClient;

    private String user = "android-house-tasks-tracker";
    private String password = "test1234";
    private String credentials;

    @Inject
    UserService(UserClient userClient) {
        this.userClient = userClient;
        this.credentials = Credentials.basic(user, password);
    }

    public Single<User> registerUser(UserRequest userRequest) {
        return userClient.registerUser(userRequest);
    }

    public Single<Token> loginUser(LoginRequest loginRequest) {
        Map<String, RequestBody> parameters = new HashMap<>();
        MediaType type = MediaType.parse("text/plain");
        parameters.put("grant_type", RequestBody.create(type, "password"));
        parameters.put("scope", RequestBody.create(type, "webclient"));
        parameters.put("username", RequestBody.create(type, loginRequest.getUsername()));
        parameters.put("password", RequestBody.create(type, loginRequest.getPassword()));

        return userClient.loginUser(credentials, parameters);
    }

}
