package ru.justd.testtask.common;

import retrofit2.http.GET;
import ru.justd.testtask.index.model.remote.FetchMemebersResponse;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */

public interface APIService {

    @GET("members.php")
    Single<FetchMemebersResponse> fetchMembers();

}
