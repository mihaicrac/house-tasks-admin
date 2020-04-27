package mihaic.com.example.house_tasks_admin.ui.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import mihaic.com.example.house_tasks_admin.ui.admin.home.groupoverview.GroupModel;
import mihaic.com.example.house_tasks_admin.ui.admin.home.rules.RulesFragment;
import mihaic.com.example.house_tasks_admin.ui.admin.home.users.UsersFragment;

public class DemoCollectionAdapter extends FragmentStateAdapter {
    private GroupModel groupModel;

    public DemoCollectionAdapter(Fragment fragment, GroupModel groupModel) {
        super(fragment);
        this.groupModel = groupModel;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment;
        if (position == 1) {
            fragment = new RulesFragment(groupModel);
        } else {
            fragment = new UsersFragment(groupModel);
        }
//        Bundle args = new Bundle();
//        // Our object is just an integer :-P
//        args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
