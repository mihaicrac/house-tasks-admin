package mihaic.com.example.house_tasks_admin.ui.admin.home.users;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mihaic.com.example.house_tasks_admin.data.users.User;

public interface UsersProvider {
    MutableLiveData<List<User>> getUsers();
}
