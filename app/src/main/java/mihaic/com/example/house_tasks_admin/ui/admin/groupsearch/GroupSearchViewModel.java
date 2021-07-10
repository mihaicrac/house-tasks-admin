package mihaic.com.example.house_tasks_admin.ui.admin.groupsearch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GroupSearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GroupSearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}