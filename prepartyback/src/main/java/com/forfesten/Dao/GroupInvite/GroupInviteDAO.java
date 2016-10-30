package com.forfesten.Dao.GroupInvite;

import com.forfesten.Models.GroupInvite;

import java.util.List;

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
    GroupInvite get(String userId, int groupId);

    /**
     * Get all users group invites
     *
     * @param userId id of user
     * @return List of invites
     */
    List<GroupInvite> getAll(String userId);

    /**
     * Get combined data for groupinvite, group and mood for groupinvites for a user
     *
     * @param userId id of user
     * @return List of all data
     */
    List<Object> getAllFullData(String userId);

    /**
     * Deletes a groupinvite
     *
     * @param userId  id of user
     * @param groupId id of group
     */
    void deleteInvite(String userId, int groupId);
}
