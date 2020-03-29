package mihaic.com.example.house_tasks_admin.di;

import dagger.Subcomponent;
import mihaic.com.example.house_tasks_admin.ui.login.LoginActivity;

@Subcomponent
public interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        LoginComponent create();
    }

    void inject(LoginActivity loginActivity);
}