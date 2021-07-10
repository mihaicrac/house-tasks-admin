package mihaic.com.example.house_tasks_admin.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mihaic.com.example.house_tasks_admin.network.taskrules.TaskRulesClient;
import mihaic.com.example.house_tasks_admin.network.taskrules.dto.RuleDto;

@Singleton
public class TaskRulesRepository {
    private TaskRulesClient client;

    @Inject
    public TaskRulesRepository(TaskRulesClient client) {
        this.client = client;
    }

    public Disposable addRule(RuleDto rule, io.reactivex.functions.Consumer<Rule> onNext, io.reactivex.functions.Consumer<Throwable> onError) {
        Disposable disposable = client.addRule(rule)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
        return disposable;
    }

    public Disposable getRulesByGroupId(String groupId, io.reactivex.functions.Consumer<List<Rule>> onNext, io.reactivex.functions.Consumer<Throwable> onError) {
        Disposable disposable = client.getRulesByGroupId(groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
        return disposable;
    }
}
