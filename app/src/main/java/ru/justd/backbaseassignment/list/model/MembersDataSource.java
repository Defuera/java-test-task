package ru.justd.backbaseassignment.list.model;

import ru.justd.backbaseassignment.list.model.remote.FetchMemebersResponse;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */

interface MembersDataSource {
    Single<FetchMemebersResponse> fetchMembers();

    void store(FetchMemebersResponse response);
}
