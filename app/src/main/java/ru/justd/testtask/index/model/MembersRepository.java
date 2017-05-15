package ru.justd.testtask.index.model;

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

    private final MembersDataSource remote;
    private final MembersDataSource local;

    @Inject
    public MembersRepository(MembersDataSource remote, MembersDataSource local) {
        this.remote = remote;
        this.local = local;
    }

    /**
     * @return list of {@link Department}s from cache or from network if cache is not awailable
     */
    public Single<List<Department>> fetchMembers() {

        return local
                .fetchMembers()
                .onErrorResumeNext(
                        throwable -> {
                            if (throwable instanceof EmptyCacheException) {
                                return remote
                                        .fetchMembers()
                                        .doOnSuccess(local::store);
                            } else {
                                throw Exceptions.propagate(throwable);
                            }

                        }

                )
                .map(response -> response.departments);
    }

}
