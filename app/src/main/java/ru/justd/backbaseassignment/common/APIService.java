package ru.justd.backbaseassignment.common;

import java.util.List;

import retrofit2.http.GET;
import ru.justd.backbaseassignment.list.model.Member;
import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */

public interface APIService {

    @GET("members.php")
    Single<List<Member>> fetchMembers();

//    @FormUrlEncoded
//    @POST("sessions")
//    Observable<LoginResponse> login(
//            @Field("token") String token,
//            @Field("provider") LoginProvider provider
//    );

}
