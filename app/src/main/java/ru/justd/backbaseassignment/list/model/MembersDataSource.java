package ru.justd.backbaseassignment.list.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.justd.backbaseassignment.common.APIService;
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

    public Single<List<Member>> fetchMembers() {
        return apiService.fetchMembers();
    }
}
