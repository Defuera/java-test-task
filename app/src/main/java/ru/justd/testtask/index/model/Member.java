package ru.justd.testtask.index.model;

import java.io.Serializable;

/**
 * Created by defuera on 20/04/2017.
 */

public class Member implements Serializable {

    public final String name;
    public final String surname;
    public final String email;
    public final String photo;
    public final String role;

    public Member(String name, String surname, String email, String photo, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.photo = photo;
        this.role = role;
    }

}
