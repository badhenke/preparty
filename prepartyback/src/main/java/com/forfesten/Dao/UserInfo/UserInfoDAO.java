package com.forfesten.Dao.UserInfo;

import com.forfesten.Models.User;
import com.forfesten.Models.UserInfo;

import java.util.List;

/**
 * Abstract for UserInfoDaoImpl
 */
public interface UserInfoDAO {

    /**
     * Save a userInfo for a specific user.
     * @param userInfo of a user
     */
    void save(UserInfo userInfo);

    /**
     * Gets UserInfo of a specific user. Returns Null of not exists.
     * @param id of User
     * @return UserInfo object
     */
    UserInfo getById(String id);

    /**
     * Update all userInfo fields for a specific user.
     * @param userInfo to update
     */
    void updateAll(UserInfo userInfo);

    /**
     * Updates GPS data for a specific user.
     * @param userId ID of user to update
     * @param gpsLatitude new latitude coordinate
     * @param gpsLongitude new longitude coordinate
     */
    void updateGps(String userId, Double gpsLatitude, Double gpsLongitude);

    /**
     * Updates email data for a specific user.
     * @param userId ID of user to update
     * @param email new email
     */
    void updateEmail(String userId, String email);

    void updateDescription(String userId, String description);
}
