package mihaic.com.example.house_tasks_admin.network.notifications;

import javax.inject.Singleton;

import io.reactivex.Single;
import mihaic.com.example.house_tasks_admin.network.notifications.dto.RegisterTokenDto;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Singleton
public interface NotificationsClient {

    @POST("notifications-service/notifications/tokens")
    Single<Void> registerToken(@Body RegisterTokenDto registerTokenDto);
}
