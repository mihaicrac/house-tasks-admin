package mihaic.com.example.house_tasks_admin.di;

import javax.inject.Singleton;

import dagger.Component;
import mihaic.com.example.house_tasks_admin.services.UserService;

@Singleton
@Component(modules = AdminModule.class)
public interface AdminModuleComponent {
    UserService adminService();
}
