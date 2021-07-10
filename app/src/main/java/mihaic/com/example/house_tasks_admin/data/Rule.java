package mihaic.com.example.house_tasks_admin.data;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Rule {

    private UUID id;

    private String name;

    private UUID groupId;

    private List<RuleItem> orderRuleItems;

    private int offset;

    @Data
    public static class RuleItem {

        private UUID userId;

        private Integer orderId;

        private int outOfOrderOccurrences;

    }

}