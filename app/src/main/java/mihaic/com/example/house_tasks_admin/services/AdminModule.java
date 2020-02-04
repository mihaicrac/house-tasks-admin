package mihaic.com.example.house_tasks_admin.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class AdminModule {

    @Provides
    static AdminClient provideRegisterService() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()//.addInterceptor(new MockInterceptor())
                        .build())
                .baseUrl("http://192.168.100.5:8084/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        return retrofit.create(AdminClient.class);
    }
}
