package mihaic.com.example.house_tasks_admin.ui.admin.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import mihaic.com.example.house_tasks_admin.MyApplication;
import mihaic.com.example.house_tasks_admin.R;
import mihaic.com.example.house_tasks_admin.data.GroupsRepository;
import mihaic.com.example.house_tasks_admin.ui.admin.home.GroupListAdapter;

public class GalleryFragment extends Fragment {

    @Inject
    GroupsRepository groupsRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MyApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        final ListView groupsListView = root.findViewById(R.id.search_groups);


        SearchView searchView = root.findViewById(R.id.search_group);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                groupsRepository.getGroupByName(query, list -> {
                    GroupListAdapter groupListAdapter = new GroupListAdapter(getActivity(), container, R.layout.group_list_item, list, groupsRepository, true);
                    groupsListView.setAdapter(groupListAdapter);
                }, error -> {
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return root;
    }


}