package mihaic.com.example.house_tasks_admin;

import android.app.Application;

import mihaic.com.example.house_tasks_admin.di.AppModule;
import mihaic.com.example.house_tasks_admin.di.ApplicationComponent;
import mihaic.com.example.house_tasks_admin.di.DaggerApplicationComponent;

public class MyApplication extends Application {
    // Reference to the application graph that is used across the whole app
    private ApplicationComponent appComponent;

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
    }
}
