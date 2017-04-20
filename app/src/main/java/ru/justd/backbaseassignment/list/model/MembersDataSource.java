package ru.justd.backbaseassignment.list.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.justd.backbaseassignment.common.APIService;
import ru.justd.backbaseassignment.list.model.remote.FetchMemebersResponse;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
class MembersDataSource {

    @Inject
    APIService apiService;

    @Inject
    MembersDataSource (){}

    public Single<FetchMemebersResponse> fetchMembers() {
        return apiService.fetchMembers();
    }
}
