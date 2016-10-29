package com.forfesten.Dao.Group;

import com.forfesten.Models.Group;

/**
 * Abstract for UserDAOImpl.
 */
interface GroupDAO {

    /**
     * Saves a new Group and returns id
     * @param group info
     * @return id of new group
     */
    int save(Group group);

    /**
     * Delete a group
     * @param id of group
     */
    void deleteGroup(int id);

    /**
     * Get a specific Group
     * @param id of group
     * @return Group
     */
    Group getById(int id);

    /**
     * Updates all info in a Group
     * @param group to update
     */
    void updateAll(Group group);
}
