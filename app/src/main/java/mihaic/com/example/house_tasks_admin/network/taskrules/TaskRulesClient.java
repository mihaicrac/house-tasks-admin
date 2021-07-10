package mihaic.com.example.house_tasks_admin.network.taskrules;

import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.network.taskrules.dto.RuleDto;
import retrofit2.http.*;

@Singleton
public interface TaskRulesClient {

    @POST("tasks-rules/order-rules")
    Single<Rule> addRule(@Body RuleDto rule);

    @GET("tasks-rules/order-rules")
    Single<List<Rule>> getRulesByGroupId(@Query("groupId") String groupId);

    @GET("tasks-rules/order-rules/user")
    Single<List<Rule>> getRulesByUserId();

    @POST("tasks-rules/order-rule-events/{ruleId}")
    Single<Rule> sendDoneEvent(@Path("ruleId") UUID ruleId);
}
