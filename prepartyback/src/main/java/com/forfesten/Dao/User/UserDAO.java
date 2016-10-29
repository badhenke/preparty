package com.forfesten.Dao.User;

import com.forfesten.Models.User;

import java.util.List;

/**
 * Abstract for UserDAOImpl.
 */
public interface UserDAO {

    /**
     * Saves a user to the database.
     * @param user to save
     */
    void save(User user);

    /**
     * Gets all users from database.
     * @return List of Users
     */
    List<User> getAll();

    /**
     * Deletes all users from database.
     */
    void deleteAll();

    /**
     * Gets a user from database, returns null if not exists.
     * @param id of user
     * @return User object
     */
    User getUserById(String id);

    /**
     * Checks if a user exists in database.
     * @param id of User
     * @return true if exists, false otherwise
     */
    boolean existById(String id);

    /**
     * Updates groupId for user
     * @param id of User
     * @param groupId of group
     */
    void setGroupId(String id, int groupId);

    /**
     * Checks if user is in a group or not.
     * @param id of user
     * @return status
     */
    boolean isInAGroup(String id);
}
