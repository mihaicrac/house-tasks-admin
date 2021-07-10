package mihaic.com.example.house_tasks_admin.ui.admin.groups.rule_items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.users.User;
import mihaic.com.example.house_tasks_admin.network.taskrules.dto.RuleDto;
import mihaic.com.example.house_tasks_admin.ui.admin.groups.groupoverview.GroupModel;

public class RulesItemsFragment extends Fragment {

    private String ruleName;
    private String ruleId;

    @Inject
    GroupModel groupModel;

    public RulesItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        ruleName = RulesItemsFragmentArgs.fromBundle(getArguments()).getRuleName();
        ruleId = RulesItemsFragmentArgs.fromBundle(getArguments()).getRuleId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemsView = inflater.inflate(R.layout.fragment_rule_items, container, false);
        ListView ruleItems = itemsView.findViewById(R.id.rule_items);

        RuleItemsAdapter adapter = new RuleItemsAdapter(getActivity(), R.layout.rule_list_item, groupModel.getUserList().getValue());
        ruleItems.setAdapter(adapter);

        Button saveRule = itemsView.findViewById(R.id.save_rule);
        saveRule.setOnClickListener(v -> {
            RuleDto rule = new RuleDto();
            List<User> currentItems = adapter.getCurrentItemsOrder();
            List<RuleDto.OrderRuleItemDto> itemsDto = IntStream.range(0, currentItems.size()).mapToObj(i -> {
                RuleDto.OrderRuleItemDto item = new RuleDto.OrderRuleItemDto();
                item.setOrderId(i);
                item.setUserId(currentItems.get(i).getId());
                return item;
            }).collect(Collectors.toList());
            rule.setOrderRuleItems(itemsDto);
            rule.setName(ruleName);
            rule.setGroupId(groupModel.getGroupId().getValue());
            groupModel.addRule(rule, r -> goBack(itemsView));
        });

        return itemsView;

    }

    public void goBack(View view) {
        Navigation.findNavController(view).navigateUp();
    }

}
