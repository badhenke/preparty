package com.forfesten.Models;

/**
 * Model for Group
 */
public class Group {

    private int id;
    private String description;
    private int moodId;

    public Group(int id, String description, int moodId) {
        this.id = id;
        this.description = description;
        this.moodId = moodId;
    }

    public Group(String description, int moodId) {
        this.description = description;
        this.moodId = moodId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public int getMoodId() {
        return moodId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
