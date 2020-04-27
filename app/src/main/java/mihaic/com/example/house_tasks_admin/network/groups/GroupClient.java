package mihaic.com.example.house_tasks_admin.network.groups;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.data.Group;
import mihaic.com.example.house_tasks_admin.data.users.User;
import retrofit2.http.*;

@Singleton
public interface GroupClient {

    @GET("authentication/groups")
    Single<List<Group>> getGroups();

    @POST("authentication/groups")
    Single<Group> addGroup(@Body Group group);

    @GET("authentication/groups/{id}/users")
    Single<List<User>> getUsers(@Path("id") String groupId);

    @GET("authentication/groups")
    Single<List<Group>> getGroups(@Query("name") String name);

    @POST("authentication/groups/{id}/user")
    Single<Void> joinGroup(@Path("id") String groupId);

}
