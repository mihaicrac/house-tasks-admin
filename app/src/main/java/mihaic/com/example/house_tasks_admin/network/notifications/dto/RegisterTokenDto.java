package mihaic.com.example.house_tasks_admin.network.notifications.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class RegisterTokenDto {

    private UUID userId;
    private String token;
}
