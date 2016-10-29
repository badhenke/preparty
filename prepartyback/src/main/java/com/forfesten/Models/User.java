package com.forfesten.Models;

import java.util.Date;

/**
 * Database model for User.
 */
public class User {

    private String id;
    private String name;
    private Date birthdate;
    private int groupId;

    public User(String id, String name, Date birthdate, int groupId) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.groupId = groupId;
    }

    public User(String name, Date birthdate, int groupId) {
        this.name = name;
        this.birthdate = birthdate;
        this.groupId = groupId;
    }

    public User(String id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
