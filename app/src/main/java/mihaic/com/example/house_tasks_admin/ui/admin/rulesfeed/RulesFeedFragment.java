package mihaic.com.example.house_tasks_admin.ui.admin.rulesfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.Group;
import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.data.users.User;

public class RulesFeedFragment extends Fragment {

    @Inject
    RulesFeedViewModel rulesFeedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final ListView rulesFeedView = root.findViewById(R.id.rules_feed_list);

        rulesFeedViewModel.getRules().observe(getViewLifecycleOwner(), map -> {
                    List<RulesFeedAdapter.RuleFeedItem> list = map.entrySet().stream().map(this::createRuleFeedItem).collect(Collectors.toList());
                    RulesFeedAdapter adapter = new RulesFeedAdapter(rulesFeedViewModel, getActivity(), R.layout.rule_list_item, list);
                    rulesFeedView.setAdapter(adapter);
                }
        );

        rulesFeedViewModel.getRulesFeed();
        return root;
    }

    private RulesFeedAdapter.RuleFeedItem createRuleFeedItem(Map.Entry<Rule, Group> entry) {
        Rule rule = entry.getKey();
        String ruleName = rule.getName();
        UUID ruleId = rule.getId();
        UUID userId = rule.getOrderRuleItems().get(rule.getOffset()).getUserId();

        Group group = entry.getValue();
        String groupName = group.getName();
        User user = group.getUsers().stream().filter(u -> u.getId().equals(userId)).findAny().get();

        return new RulesFeedAdapter.RuleFeedItem(ruleName, ruleId, groupName, user.getUsername());

    }
}