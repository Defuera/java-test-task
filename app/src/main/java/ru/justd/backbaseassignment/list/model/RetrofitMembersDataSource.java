package ru.justd.backbaseassignment.list.model;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.justd.backbaseassignment.BuildConfig;
import ru.justd.backbaseassignment.common.APIService;
import ru.justd.backbaseassignment.list.model.remote.FetchMemebersResponse;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
public class RetrofitMembersDataSource implements MembersDataSource {

    private final APIService apiService;

    @Inject
    public RetrofitMembersDataSource(APIService apiService) {
        this.apiService = apiService;
    }

    public Single<FetchMemebersResponse> fetchMembers() {
        return apiService.fetchMembers()
                .delay(BuildConfig.DEBUG ? 2 : 0, TimeUnit.SECONDS); //for testing only
    }

    @Override
    public void store(FetchMemebersResponse response) {
        throw new UnsupportedOperationException();
    }
}
