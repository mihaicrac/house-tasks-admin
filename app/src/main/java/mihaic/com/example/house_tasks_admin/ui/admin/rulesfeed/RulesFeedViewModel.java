package mihaic.com.example.house_tasks_admin.ui.admin.rulesfeed;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mihaic.com.example.house_tasks_admin.data.Group;
import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.network.groups.GroupClient;
import mihaic.com.example.house_tasks_admin.network.taskrules.TaskRulesClient;

public class RulesFeedViewModel extends ViewModel {
    private TaskRulesClient taskRulesClient;
    private GroupClient groupClient;

    private MutableLiveData<Map<Rule, Group>> rules;

    @Inject
    public RulesFeedViewModel(TaskRulesClient taskRulesClient, GroupClient groupClient) {
        rules = new MutableLiveData<>(new HashMap<>());
        this.taskRulesClient = taskRulesClient;
        this.groupClient = groupClient;
    }

    public Disposable getRulesFeed() {
        Single<List<Rule>> rules = taskRulesClient.getRulesByUserId();
        Single<List<Group>> groups = groupClient.getGroups();
        return rules.zipWith(groups, this::createMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(m -> this.rules.setValue(m), error -> {
                });
    }

    public Disposable sendDoneEvent(UUID ruleId) {
        return taskRulesClient.sendDoneEvent(ruleId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(m -> {
                    HashMap<Rule, Group> newMap = new HashMap<>(this.getRules().getValue());
                    Rule oldRule = newMap.keySet().stream().filter(r -> r.getId().equals(ruleId)).findAny().get();
                    newMap.put(m, newMap.remove(oldRule));
                    this.getRules().setValue(newMap);
                }, error -> {
                });
    }

    private Map<Rule, Group> createMap(List<Rule> rules, List<Group> groups) {
        Map<Rule, Group> map = new HashMap<>();
        rules.forEach(r ->
                groups.stream().filter(g -> g.getId().equals(r.getGroupId())).findAny().ifPresent(g -> map.put(r, g))
        );
        return map;
    }

    public MutableLiveData<Map<Rule, Group>> getRules() {
        return rules;
    }
}