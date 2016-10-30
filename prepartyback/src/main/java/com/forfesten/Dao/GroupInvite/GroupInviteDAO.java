package com.forfesten.Dao.GroupInvite;

import com.forfesten.Models.GroupInvite;

/**
 * Interface for Group Invite database interaction
 */
interface GroupInviteDAO {

    /**
     * Saves a group invite
     *
     * @param groupInvite to save
     */
    void save(GroupInvite groupInvite);

    /**
     * Get a groupinvite from user by groupid.
     *
     * @param userId  id of user
     * @param groupId id of group
     * @return GroupInvite, Null if not exist.
     */
    GroupInvite getGroupInvite(String userId, int groupId);
}
