package mihaic.com.example.house_tasks_admin.network.groups;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.data.Group;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

@Singleton
public interface GroupClient {

    @GET("groups")
    Single<List<Group>> getGroups();

    @POST("groups")
    Single<Group> addGroup(@Body Group group);

}
