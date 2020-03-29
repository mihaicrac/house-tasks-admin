package mihaic.com.example.house_tasks_admin.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import mihaic.com.example.house_tasks_admin.data.Result;
import mihaic.com.example.house_tasks_admin.data.users.UserRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Result> loginResult = new MutableLiveData<>();
    private UserRepository userRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    LiveData<Result> getLoginResult() {
        return loginResult;
    }

    public boolean isLoggedId() {
        return userRepository.isLoggedIn();
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        disposables.add(userRepository.login(username, password,
                result -> loginResult.setValue(result))
        );
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
