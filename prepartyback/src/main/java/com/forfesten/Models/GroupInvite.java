package com.forfesten.Models;

/**
 * Model for Group Invites
 */
public class GroupInvite {

    private int id;
    private int userId;
    private int groupId;

    public GroupInvite(int id, int userId, int groupId) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
    }

    public GroupInvite(int userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }
}
