package mihaic.com.example.house_tasks_admin.ui.admin.rulesfeed;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import mihaic.com.example.house_tasks_admin.R;

public class RulesFeedAdapter extends ArrayAdapter<RulesFeedAdapter.RuleFeedItem> {

    private Activity context;
    private List<RuleFeedItem> rulesFeed;
    private RulesFeedViewModel rulesFeedViewModel;

    static class RuleFeedItem {
        String ruleName;
        UUID ruleId;
        String groupName;
        String currentUserName;

        public RuleFeedItem(String ruleName, UUID ruleId, String groupName, String currentUserName) {
            this.ruleName = ruleName;
            this.ruleId = ruleId;
            this.groupName = groupName;
            this.currentUserName = currentUserName;
        }
    }


    public RulesFeedAdapter(RulesFeedViewModel rulesFeedViewModel, Activity context, int resource, List<RuleFeedItem> rulesFeed) {
        super(context, resource, rulesFeed);
        this.context = context;
        this.rulesFeed = rulesFeed;
        this.rulesFeedViewModel = rulesFeedViewModel;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.rule_list_item, null, true);

        TextView ruleName = rowView.findViewById(R.id.rule_name);
//     set enabled or disabled  Button leaveButton = rowView.findViewById(R.id.leave_group_button);

        ruleName.setText(rulesFeed.get(position).ruleName + " (" + rulesFeed.get(position).groupName + ") ");


        TextView ruleDuty = rowView.findViewById(R.id.rule_duty);
        ruleDuty.setText(rulesFeed.get(position).currentUserName);

        rowView.findViewById(R.id.update).setVisibility(View.INVISIBLE);
        Button notify = rowView.findViewById(R.id.delete);
        notify.setText("notify");


        notify.setOnClickListener(v ->
                rulesFeedViewModel.sendDoneEvent(rulesFeed.get(position).ruleId)
        );

        return rowView;

    }


}
