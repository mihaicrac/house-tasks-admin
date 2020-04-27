package mihaic.com.example.house_tasks_admin.ui.admin.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
                    GroupListAdapter groupListAdapter = new GroupListAdapter(getActivity(), container, R.layout.fragment_home, list, null, false);
                    groupsView.setAdapter(groupListAdapter);

                }
        );

        final TextView addGroupText = root.findViewById(R.id.add_group_text);

        root.findViewById(R.id.add_group_button).setOnClickListener(v -> {
            Group g = new Group(null, addGroupText.getText().toString());
            homeViewModel.addGroup(g);
            hideKeyboardFrom(getContext(), root);
        });

        root.setOnClickListener(v -> hideKeyboardFrom(getContext(), root));

        return root;
    }

    private void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}