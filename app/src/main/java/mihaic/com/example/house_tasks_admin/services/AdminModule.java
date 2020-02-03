package mihaic.com.example.house_tasks_admin.services;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class AdminModule {

    @Provides
    static AdminClient provideRegisterService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new MockInterceptor())
                        .build())
                .baseUrl("http://192.168.100.5:8084/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(AdminClient.class);
    }
}
