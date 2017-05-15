package ru.justd.testtask.index.model.remote;

import java.util.List;

import ru.justd.testtask.index.model.Department;

/**
 * Created by defuera on 20/04/2017.
 */

public class FetchMemebersResponse {

    public final List<Department> departments;

    public FetchMemebersResponse(List<Department> departments) {this.departments = departments;}
}
