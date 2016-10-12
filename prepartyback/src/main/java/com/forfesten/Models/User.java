package com.forfesten.Models;

import java.util.Date;

/**
 * Created by heer on 12/10/2016.
 */
public class User {

    private int id;
    private String name;
    private Date birthdate;

    public User(int id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }
}
