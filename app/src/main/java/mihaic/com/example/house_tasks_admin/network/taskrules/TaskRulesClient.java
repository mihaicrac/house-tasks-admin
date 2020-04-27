package mihaic.com.example.house_tasks_admin.network.taskrules;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.network.taskrules.dto.RuleDto;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Singleton
public interface TaskRulesClient {

    @POST("tasks-rules/order-rules")
    Single<Rule> addRule(@Body RuleDto rule);

    @GET("tasks-rules/order-rules")
    Single<List<Rule>> getRules(@Query("groupId") String groupId);
}
