package ru.justd.backbaseassignment.list.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.justd.backbaseassignment.list.model.remote.FetchMemebersResponse;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
public class MembersInMemoryDataSource implements MembersDataSource {

    private FetchMemebersResponse response;

    @Inject
    public MembersInMemoryDataSource() {}

    public Single<FetchMemebersResponse> fetchMembers() {
        if (response == null) {
            return Single.error(new EmptyCacheException());
        } else {
            return Single.just(response);
        }
    }

    public void store(FetchMemebersResponse response) {
        this.response = response;
    }
}
