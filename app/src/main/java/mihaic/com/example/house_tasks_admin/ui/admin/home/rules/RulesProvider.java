package mihaic.com.example.house_tasks_admin.ui.admin.home.rules;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.data.users.User;

public interface RulesProvider {
    MutableLiveData<List<Rule>> getRules();
    MutableLiveData<List<User>> getUsers();
}
