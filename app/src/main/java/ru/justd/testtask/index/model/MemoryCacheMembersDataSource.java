package ru.justd.testtask.index.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.justd.testtask.index.model.remote.FetchMemebersResponse;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
public class MemoryCacheMembersDataSource implements MembersDataSource {

    private FetchMemebersResponse response;

    @Inject
    public MemoryCacheMembersDataSource() {}

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
