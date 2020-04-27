package mihaic.com.example.house_tasks_admin.network.taskrules.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class RuleDto {
    private List<OrderRuleItemDto> orderRuleItems;
    private String name;
    private String groupId;

    @Data
    public static class OrderRuleItemDto {
        private UUID userId;

        private Integer orderId;

    }

}
