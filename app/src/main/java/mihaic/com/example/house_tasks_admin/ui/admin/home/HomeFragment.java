package mihaic.com.example.house_tasks_admin.ui.admin.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.Group;

public class HomeFragment extends Fragment {
    @Inject
    HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView groupsView = root.findViewById(R.id.listview);
        homeViewModel.getGroups();
        homeViewModel.getGroupList().observe(getViewLifecycleOwner(), list -> {
                    List<String> groups = list.stream().map(g ->
                            g.getId() + ":" + g.getName()).collect(Collectors.toList());
                    ArrayAdapter<List<String>> arrayAdapter = new ArrayAdapter(getContext(), R.layout.group_list_item, groups);
                    groupsView.setAdapter(arrayAdapter);
                }
        );
        homeViewModel.addGroup(new Group(null, "Group"+Math.random()));
        return root;
    }
}