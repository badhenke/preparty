package com.forfesten.Models;

/**
 * Model for Mood
 */
public class Mood {

    private int id;
    private String name;

    public Mood(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Mood(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
