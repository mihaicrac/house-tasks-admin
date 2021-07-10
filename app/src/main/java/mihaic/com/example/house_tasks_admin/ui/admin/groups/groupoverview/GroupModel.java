package mihaic.com.example.house_tasks_admin.ui.admin.groups.groupoverview;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Singleton;

import mihaic.com.example.house_tasks_admin.data.GroupsRepository;
import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.data.TaskRulesRepository;
import mihaic.com.example.house_tasks_admin.data.users.User;
import mihaic.com.example.house_tasks_admin.network.taskrules.dto.RuleDto;
import mihaic.com.example.house_tasks_admin.ui.admin.groups.rules.RulesProvider;
import mihaic.com.example.house_tasks_admin.ui.admin.groups.users.UsersProvider;

@Singleton
public class GroupModel extends ViewModel implements RulesProvider, UsersProvider {
    private GroupsRepository groupsRepository;
    private TaskRulesRepository taskRulesRepository;
    private final MutableLiveData<String> groupId;
    private final MutableLiveData<List<User>> userList;
    private final MutableLiveData<List<Rule>> ruleList;

    @Inject
    public GroupModel(GroupsRepository groupsRepository, TaskRulesRepository taskRulesRepository) {
        this.groupsRepository = groupsRepository;
        this.taskRulesRepository = taskRulesRepository;
        userList = new MutableLiveData<>(new ArrayList<>());
        ruleList = new MutableLiveData<>(new ArrayList<>());
        groupId = new MutableLiveData<>("");
    }

    public MutableLiveData<List<User>> getUserList() {
        return userList;
    }

    public MutableLiveData<List<Rule>> getRuleList() {
        return ruleList;
    }

    public MutableLiveData<String> getGroupId() {
        return groupId;
    }

    public void getUsers(String id) {
        groupsRepository.getUsers(id, list -> userList.setValue(list),
                error -> userList.setValue(new ArrayList<>()));
    }

    public void getRules(String groupId) {
        taskRulesRepository.getRulesByGroupId(groupId, list -> ruleList.setValue(list),
                error -> {
                    System.out.println(error);
                });
    }

    public void addRule(RuleDto rule, Consumer<Rule> callback) {
        taskRulesRepository.addRule(rule, r -> {
            getRules().getValue().add(r);
            getRuleList().setValue(new ArrayList<>(getRules().getValue()));
            callback.accept(r);
        }, error -> {
            System.out.println(error);
        });
    }

    @Override
    public MutableLiveData<List<Rule>> getRules() {
        return ruleList;
    }

    @Override
    public MutableLiveData<List<User>> getUsers() {
        return userList;
    }
}