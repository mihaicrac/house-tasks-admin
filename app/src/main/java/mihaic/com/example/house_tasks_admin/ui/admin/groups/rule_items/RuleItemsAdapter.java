package mihaic.com.example.house_tasks_admin.ui.admin.groups.rule_items;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.users.User;

public class RuleItemsAdapter extends ArrayAdapter<User> {

    private Activity context;
    private List<User> users;
    private List<User> currentItems;

    public RuleItemsAdapter(Activity context, int resource, List<User> users) {
        super(context, resource, users);
        this.context = context;
        this.users = users;
        this.currentItems = new ArrayList(users);
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.rule_items_list_item, null, true);

        Spinner ruleItem = rowView.findViewById(R.id.order_item);

        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, users.stream().map(r -> r.getUsername()).collect(Collectors.toList()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ruleItem.setAdapter(adapter);
        ruleItem.setSelection(position);


        ruleItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                currentItems.set(position, users.get(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return rowView;

    }

    public List<User> getCurrentItemsOrder() {
        return currentItems;
    }

}