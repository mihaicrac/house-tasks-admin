package mihaic.com.example.house_tasks_admin.ui.admin.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.List;

import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.Group;
import mihaic.com.example.house_tasks_admin.data.GroupsRepository;

public class GroupListAdapter extends ArrayAdapter<Group> {

    private Activity context;
    private List<Group> groups;
    private ViewGroup container;
    private GroupsRepository groupsRepository;
    private boolean searchFragment;

    public GroupListAdapter(Activity context, ViewGroup container, int resource, List<Group> groups, GroupsRepository groupsRepository, boolean searchFragment) {
        super(context, resource, groups);
        this.context = context;
        this.groups = groups;
        this.container = container;
        this.groupsRepository = groupsRepository;
        this.searchFragment = searchFragment;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.group_list_item, null, true);

        TextView groupName = rowView.findViewById(R.id.group_name);
        Group group = groups.get(position);
        groupName.setText(group.getName());

        if (searchFragment) {
            showJoinButton(rowView, group);
        } else {
            showDetailsButton(rowView, group);
            showLeaveButton(rowView, group);
        }
        return rowView;

    }


    private void showDetailsButton(View rowView, Group group) {
        Button details = rowView.findViewById(R.id.details);
        details.setVisibility(View.VISIBLE);

        rowView.findViewById(R.id.details).setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionNavHomeToUsersAndRulesFragment(group.getId().toString());
            Navigation.findNavController(container).navigate(action);
        });
    }

    private void showLeaveButton(View rowView, Group group) {
        Button leave = rowView.findViewById(R.id.join_leave_group_button);
        leave.setText("LEAVE");
        leave.setVisibility(View.VISIBLE);
    }

    private void showJoinButton(View rowView, Group group) {
        Button join = rowView.findViewById(R.id.join_leave_group_button);
        join.setText("JOIN");
        join.setVisibility(View.VISIBLE);

        join.setOnClickListener(v -> {
            groupsRepository.joinGroup(group.getId().toString(), res -> {
            }, error -> {
            });
            join.setText("LEAVE");
        });

    }
}
