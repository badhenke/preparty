package com.forfesten.Models;

/**
 * Model for Group
 */
public class Group {

    private int id;
    private String description;

    public Group(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
