package com.forfesten.Models;

import java.util.Date;

/**
 * Model for Group Invites
 */
public class GroupInvite {

    private int id;
    private String userId;
    private int groupId;
    private Date created;

    public GroupInvite(int id, String userId, int groupId, Date created) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
        this.created = created;
    }

    public GroupInvite(String userId, int groupId, Date created) {
        this.userId = userId;
        this.groupId = groupId;
        this.created = created;
    }

    public GroupInvite(String userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public Date getCreated() {
        return created;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }
}
