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
public class MembersRepository {

    private final MembersDataSource remoteDataSource;
    private final MembersDataSource localDataSource;

    @Inject
    public MembersRepository(MembersDataSource remoteDataSource, MembersDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public Single<List<Department>> fetchMembers() {

        return localDataSource
                .fetchMembers()
                .onErrorResumeNext(
                        throwable -> {
                            if (throwable instanceof EmptyCacheException) {
                                return remoteDataSource
                                        .fetchMembers()
                                        .doOnSuccess(localDataSource::store);
                            } else {
                                throw Exceptions.propagate(throwable);
                            }

                        }

                )
                .map(response -> response.departments);
    }

}
