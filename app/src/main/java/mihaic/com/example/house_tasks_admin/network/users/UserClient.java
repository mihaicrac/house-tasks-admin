package mihaic.com.example.house_tasks_admin.network.users;

import java.util.Map;

import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.data.Token;
import mihaic.com.example.house_tasks_admin.ui.register.User;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

@Singleton
public interface UserClient {
    @POST("users")
    Single<User> registerUser(@Body UserRequest userRequest);

    @Multipart
    @POST("oauth/token")
    Single<Token> loginUser(@Header("Authorization") String header, @PartMap Map<String, RequestBody> parameters);

}
