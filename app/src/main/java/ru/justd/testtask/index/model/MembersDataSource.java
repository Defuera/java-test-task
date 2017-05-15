package ru.justd.testtask.index.model;

import ru.justd.testtask.index.model.remote.FetchMemebersResponse;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */

interface MembersDataSource {
    Single<FetchMemebersResponse> fetchMembers();

    void store(FetchMemebersResponse response);
}
