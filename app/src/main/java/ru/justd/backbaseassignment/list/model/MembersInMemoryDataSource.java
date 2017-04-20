package ru.justd.backbaseassignment.list.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
public class MembersInMemoryDataSource {

    private List<Department> departments;

    @Inject
    MembersInMemoryDataSource() {}

    public Single<List<Department>> fetchMembers() {
        if (departments == null) {
            return Single.error(new EmptyCacheException());
        } else {
            return Single.just(departments);
        }
    }

    public void storeMembers(List<Department> departments) {
        this.departments = departments;
    }
}
