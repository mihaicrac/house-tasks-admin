package mihaic.com.example.house_tasks_admin.ui.admin.groups.rules;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.Rule;
import mihaic.com.example.house_tasks_admin.data.users.User;

public class RulesAdapter extends ArrayAdapter<Rule> {

    private Activity context;
    private List<Rule> rules;
    private List<User> users;

    public RulesAdapter(Activity context, int resource, List<Rule> rules, List<User> users) {
        super(context, resource, rules);
        this.context = context;
        this.rules = rules;
        this.users = users;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.rule_list_item, null, true);

        TextView ruleName = rowView.findViewById(R.id.rule_name);
//     set enabled or disabled  Button leaveButton = rowView.findViewById(R.id.leave_group_button);

        ruleName.setText(rules.get(position).getName());

        TextView ruleDuty = rowView.findViewById(R.id.rule_duty);
        int offset = rules.get(position).getOffset();
        String id = rules.get(position).getOrderRuleItems().get(offset).getUserId().toString();
        ruleDuty.setText(users.stream().filter(u -> u.getId().toString().equals(id)).findAny().get().getUsername());
        return rowView;

    }
}