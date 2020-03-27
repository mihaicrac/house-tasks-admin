package mihaic.com.example.house_tasks_admin.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import mihaic.com.example.house_tasks_admin.data.Result;
import mihaic.com.example.house_tasks_admin.data.users.UserRepository;
import mihaic.com.example.house_tasks_admin.data.Token;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private UserRepository userRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public boolean isLoggedId() {
        return userRepository.isLoggedIn();
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        disposables.add(userRepository.login(username, password, result -> {
                    if (result instanceof Result.Success) {
                        loginResult.setValue(new LoginResult((Token) ((Result.Success) result).getData()));
                    } else {
                        loginResult.setValue(new LoginResult(((Result.Error) result).getError()));
                    }
                }
        ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
