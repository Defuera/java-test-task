package ru.justd.backbaseassignment.list.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;
import rx.exceptions.Exceptions;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
public class MembersInteractor {

    @Inject
    MembersRemoteDataSource remoteDataSource;

    @Inject
    MembersInMemoryDataSource localDataSource;

    @Inject
    MembersInteractor() {}

    public Single<List<Department>> fetchMembers() {

        return localDataSource
                .fetchMembers()
                .onErrorResumeNext(
                        throwable -> {
                            if (throwable instanceof EmptyCacheException) {
                                return remoteDataSource
                                        .fetchMembers()
                                        .map(response -> response.departments)
                                        .doOnSuccess(departments -> localDataSource.storeMembers(departments));
                            } else {
                                throw Exceptions.propagate(throwable);
                            }

                        }

                );
    }

}
