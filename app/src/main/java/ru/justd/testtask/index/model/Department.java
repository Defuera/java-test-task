package ru.justd.testtask.index.model;

import java.util.List;

/**
 * Created by defuera on 20/04/2017.
 */
public class Department {
    public final String name;
    public final List<Member> members;

    public Department(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }
}
