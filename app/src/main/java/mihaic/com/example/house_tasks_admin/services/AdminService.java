package mihaic.com.example.house_tasks_admin.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.ui.register.User;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class AdminService {

    private AdminClient adminClient;

    private String user = "android-house-tasks-tracker";
    private String password = "test1234";
    private String credentials;

    @Inject
    AdminService(AdminClient adminClient) {
        this.adminClient = adminClient;
        this.credentials = Credentials.basic(user, password);

    }

    public Call<User> registerUser(UserRequest userRequest) {
        return adminClient.registerUser(userRequest);
    }

    public Call<Token> loginUser(LoginRequest loginRequest) {
        Map<String, RequestBody> parameters = new HashMap<>();
        MediaType type = MediaType.parse("text/plain");
        parameters.put("grant_type", RequestBody.create(type, "password"));
        parameters.put("scope", RequestBody.create(type, "webclient"));
        parameters.put("username", RequestBody.create(type, loginRequest.getUsername()));
        parameters.put("password", RequestBody.create(type, loginRequest.getPassword()));

        return adminClient.loginUser(credentials, parameters);
    }

}
