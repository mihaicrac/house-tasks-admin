package mihaic.com.example.house_tasks_admin.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mihaic.com.example.house_tasks_admin.network.groups.GroupClient;

@Singleton
public class GroupsRepository {

    private GroupClient groupClient;

    @Inject
    public GroupsRepository(GroupClient groupClient) {
        this.groupClient = groupClient;
    }

    public Disposable getGroups(io.reactivex.functions.Consumer<List<Group>> onNext, io.reactivex.functions.Consumer<Throwable> onError) {
        Disposable disposable = groupClient.getGroups()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
        return disposable;
    }

    public Disposable addGroup(Group group, io.reactivex.functions.Consumer<Group> onNext, io.reactivex.functions.Consumer<Throwable> onError) {
        Disposable disposable = groupClient.addGroup(group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
        return disposable;
    }
}
