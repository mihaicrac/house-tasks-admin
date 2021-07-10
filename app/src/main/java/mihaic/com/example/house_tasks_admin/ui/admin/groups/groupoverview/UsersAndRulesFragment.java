package mihaic.com.example.house_tasks_admin.ui.admin.groups.groupoverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.ui.pager.DemoCollectionAdapter;

public class UsersAndRulesFragment extends Fragment {
    @Inject
    GroupModel groupModel;

    public UsersAndRulesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String groupId = UsersAndRulesFragmentArgs.fromBundle(getArguments()).getGroupId();
        groupModel.getGroupId().setValue(groupId);
        groupModel.getUsers(groupId);
        groupModel.getRules(groupId);
        return inflater.inflate(R.layout.fragment_users_and_rules, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        DemoCollectionAdapter demoCollectionAdapter = new DemoCollectionAdapter(this, groupModel);
        ViewPager2 viewPager = getActivity().findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, consumeTab())
                .attach();

    }


    private TabLayoutMediator.TabConfigurationStrategy consumeTab() {
        return ((tab, position) -> {
            if (position == 1) {
                tab.setText("rules");
                tab.setIcon(R.drawable.ic_group_work_black_24dp);
            } else {
                tab.setText("users");
                tab.setIcon(R.drawable.ic_group_black_24dp);
            }
        });
    }
}
