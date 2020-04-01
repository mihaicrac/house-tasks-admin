package mihaic.com.example.house_tasks_admin.ui.admin.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.data.Group;
import mihaic.com.example.house_tasks_admin.data.GroupsRepository;

public class HomeViewModel extends ViewModel {
    private GroupsRepository groupsRepository;
    private final MutableLiveData<List<Group>> groupList;

    @Inject
    public HomeViewModel(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
        groupList = new MutableLiveData<>();
        groupList.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Group>> getGroupList() {
        return groupList;
    }

    public void getGroups() {
        groupsRepository.getGroups(list -> groupList.setValue(list),
                error -> groupList.setValue(new ArrayList<>()));
    }

    public void addGroup(Group group) {
        groupsRepository.addGroup(group,
                g -> groupList.getValue().add(g)
                , error -> System.out.println("error" + error.getLocalizedMessage())
        );
    }
}