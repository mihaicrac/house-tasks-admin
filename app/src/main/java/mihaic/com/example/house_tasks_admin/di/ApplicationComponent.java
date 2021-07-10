package mihaic.com.example.house_tasks_admin.di;


import javax.inject.Singleton;

import dagger.Component;
import mihaic.com.example.house_tasks_admin.data.users.UserRepository;
import mihaic.com.example.house_tasks_admin.network.notifications.NotificationsClient;
import mihaic.com.example.house_tasks_admin.services.MyFirebaseMessagingService;
import mihaic.com.example.house_tasks_admin.services.TokenStoreService;
import mihaic.com.example.house_tasks_admin.ui.admin.AdminActivity;
import mihaic.com.example.house_tasks_admin.ui.admin.groupsearch.GroupSearchFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.groups.GroupsFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.groups.groupoverview.UsersAndRulesFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.groups.rule_items.RulesItemsFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.rulesfeed.RulesFeedFragment;
import mihaic.com.example.house_tasks_admin.ui.register.RegisterActivity;

@Singleton
@Component(modules = {AppModule.class, SubComponentsModule.class})
public interface ApplicationComponent {
    UserRepository userRepository();

    LoginComponent.Factory loginComponent();

    NotificationsClient notificationsClient();

    TokenStoreService tokenStoreService();

    void inject(GroupsFragment adminActivity);

    void inject(RulesFeedFragment rulesFeedFragment);

    void inject(UsersAndRulesFragment usersAndRulesFragment);

    void inject(GroupSearchFragment groupSearchFragment);

    void inject(RulesItemsFragment rulesItemsFragment);

    void inject(AdminActivity adminActivity);

    void inject(RegisterActivity registerActivity);

    void inject(MyFirebaseMessagingService myFirebaseMessagingService);
}
