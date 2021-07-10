package mihaic.com.example.house_tasks_admin.data;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mihaic.com.example.house_tasks_admin.data.users.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private UUID id;

    private String name;

    private List<User> users;
}
