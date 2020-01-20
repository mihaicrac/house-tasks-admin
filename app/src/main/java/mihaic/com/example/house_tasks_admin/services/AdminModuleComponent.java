package mihaic.com.example.house_tasks_admin.services;

import dagger.Component;

@Component(modules = AdminModule.class)
public interface AdminModuleComponent {
    AdminService adminService();
}
