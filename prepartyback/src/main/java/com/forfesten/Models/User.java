package com.forfesten.Models;

import java.util.Date;

/**
 * Created by heer on 12/10/2016.
 */
public class User {

    private String id;
    private String name;
    private Date birthdate;

    public User(String id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public User(String name, Date birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }
}
