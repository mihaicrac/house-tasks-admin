package mihaic.com.example.house_tasks_admin.di;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.network.MockInterceptor;
import mihaic.com.example.house_tasks_admin.network.groups.GroupClient;
import mihaic.com.example.house_tasks_admin.network.taskrules.TaskRulesClient;
import mihaic.com.example.house_tasks_admin.network.users.UserClient;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(MockInterceptor mockInterceptor) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(mockInterceptor)
                        .build())
                .baseUrl("http://192.168.100.19:5555/api/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public UserClient providesUserClient(Retrofit retrofit) {
        return retrofit.create(UserClient.class);
    }

    @Provides
    @Singleton
    public GroupClient providesGroupClient(Retrofit retrofit) {
        return retrofit.create(GroupClient.class);
    }

    @Provides
    @Singleton
    public TaskRulesClient providesTaskRulesClient(Retrofit retrofit) {
        return retrofit.create(TaskRulesClient.class);
    }
}
