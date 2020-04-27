package mihaic.com.example.house_tasks_admin.ui.admin.home.users;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.users.User;

public class UsersListAdapter extends ArrayAdapter<User> {

    private Activity context;
    private List<User> users;

    public UsersListAdapter(Activity context, int resource, List<User> users) {
        super(context, resource, users);
        this.context = context;
        this.users = users;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.user_list_item, null, true);

        TextView titleText = rowView.findViewById(R.id.username);
//     set enabled or disabled  Button leaveButton = rowView.findViewById(R.id.leave_group_button);

        titleText.setText(users.get(position).getUsername());
        return rowView;

    }
}