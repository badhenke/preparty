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
}
