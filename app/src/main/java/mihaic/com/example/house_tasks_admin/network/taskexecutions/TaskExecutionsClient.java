package mihaic.com.example.house_tasks_admin.network.taskexecutions;

import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.data.Group;
import retrofit2.http.POST;
import retrofit2.http.Path;

@Singleton
public interface TaskExecutionsClient {

    @POST("task-executions/tasks/{ruleId}")
    Single<List<Group>> getGroups(@Path("ruleId") UUID ruleId);

}
