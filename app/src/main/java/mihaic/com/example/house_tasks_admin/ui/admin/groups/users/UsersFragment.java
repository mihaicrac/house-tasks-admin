package mihaic.com.example.house_tasks_admin.ui.admin.groups.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import mihaic.com.example.house_tasks_admin.R;

public class UsersFragment extends Fragment {

    UsersProvider usersProvider;

    public UsersFragment(UsersProvider usersProvider) {
        // Required empty public constructor
        this.usersProvider = usersProvider;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View usersView = inflater.inflate(R.layout.fragment_users, container);
        ListView  usersList = usersView.findViewById(R.id.rules);
        UsersListAdapter adapter = new UsersListAdapter(getActivity(), R.layout.user_list_item, new ArrayList<>());
        usersList.setAdapter(adapter);

        usersProvider.getUsers().observe(getViewLifecycleOwner(), list -> {
            adapter.clear();
            adapter.addAll(list);
        });



        return usersView;

    }
}
