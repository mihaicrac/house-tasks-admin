package mihaic.com.example.house_tasks_admin.ui.admin.home.rules;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.UUID;

import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.ui.admin.home.groupoverview.UsersAndRulesFragmentDirections;

public class RulesFragment extends Fragment {
    private RulesProvider rulesProvider;

    public RulesFragment(RulesProvider rulesProvider) {
        // Required empty public constructor
        this.rulesProvider = rulesProvider;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rulesView = inflater.inflate(R.layout.fragment_rules, container);
        ListView rulesList = rulesView.findViewById(R.id.rules);

        Rule.RuleItem item = new Rule.RuleItem();
        item.setUserId(UUID.randomUUID());
        RulesAdapter adapter = new RulesAdapter(getActivity(), R.layout.rule_list_item, rulesProvider.getRules().getValue(), rulesProvider.getUsers().getValue());

        rulesList.setAdapter(adapter);

        rulesProvider.getRules().observe(getViewLifecycleOwner(), list -> {
                    RulesAdapter rulesAdapter = new RulesAdapter(getActivity(), R.layout.rule_list_item, list, rulesProvider.getUsers().getValue());
                    rulesList.setAdapter(rulesAdapter);
                }
        );

        TextView ruleName = rulesView.findViewById(R.id.rule_name);
        Button createRule = rulesView.findViewById(R.id.create_rule);
        createRule.setOnClickListener(v ->
                startRuleFragment(rulesView, ruleName.getText().toString(), null)
        );

        return rulesView;

    }

    private void startRuleFragment(View view, String ruleName, String ruleId) {
        NavDirections action = UsersAndRulesFragmentDirections.actionUsersAndRulesFragmentToRulesItemsFragment2(ruleName, ruleId);
        Navigation.findNavController(view).navigate(action);
    }
}
