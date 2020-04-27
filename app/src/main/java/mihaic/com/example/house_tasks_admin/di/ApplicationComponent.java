package mihaic.com.example.house_tasks_admin.di;


import javax.inject.Singleton;

import dagger.Component;
import mihaic.com.example.house_tasks_admin.data.users.UserRepository;
import mihaic.com.example.house_tasks_admin.ui.admin.AdminActivity;
import mihaic.com.example.house_tasks_admin.ui.admin.gallery.GalleryFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.home.HomeFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.home.groupoverview.UsersAndRulesFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.home.rule_items.RulesItemsFragment;
import mihaic.com.example.house_tasks_admin.ui.register.RegisterActivity;

@Singleton
@Component(modules = {AppModule.class, SubComponentsModule.class})
public interface ApplicationComponent {
    UserRepository userRepository();

    LoginComponent.Factory loginComponent();

    void inject(HomeFragment adminActivity);

    void inject(UsersAndRulesFragment usersAndRulesFragment);

    void inject(GalleryFragment galleryFragment);

    void inject(RulesItemsFragment rulesItemsFragment);

    void inject(AdminActivity adminActivity);

    void inject(RegisterActivity registerActivity);
}
