package ru.justd.backbaseassignment.list.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
public class MembersInteractor {

    @Inject
    MembersDataSource dataSource;

    @Inject
    MembersInteractor() {}

    public Single<List<Member>> fetchMembers(){
        return dataSource.fetchMembers();
    }
}
