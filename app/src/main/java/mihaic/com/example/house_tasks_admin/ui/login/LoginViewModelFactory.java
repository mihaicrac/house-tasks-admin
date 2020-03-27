package mihaic.com.example.house_tasks_admin.ui.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import mihaic.com.example.house_tasks_admin.data.TokenPersister;
import mihaic.com.example.house_tasks_admin.data.users.UserRepository;
import mihaic.com.example.house_tasks_admin.di.DaggerAdminModuleComponent;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public LoginViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(UserRepository.getInstance(DaggerAdminModuleComponent.create().adminService(), TokenPersister.getInstance(context)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
