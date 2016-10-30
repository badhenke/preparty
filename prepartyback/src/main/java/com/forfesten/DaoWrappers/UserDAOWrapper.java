package com.forfesten.DaoWrappers;

import com.forfesten.Dao.User.UserDAOImpl;
import com.forfesten.Dao.UserInfo.UserInfoDAOImpl;
import com.forfesten.Facebook.Graph;
import com.forfesten.Models.User;
import com.forfesten.Models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Wrapper that should be used when doing actions to database.
 * This is because several logic can be done here before doing queries.
 */
@Service
public class UserDAOWrapper {

    @Autowired
    UserDAOImpl userDAO;

    @Autowired
    UserInfoDAOImpl userInfoDAO;

    @Autowired
    Graph graph;

    /**
     * Gets a User by its id.
     * @param id of User
     * @return User object, Null if not exist
     */
    public User getUserById(String id){
        return userDAO.getById(id);
    }

    /**
     * Creates (reads data from FB) User and UserInfo if userId does not exist
     * in database.
     * @param id of User
     * @param accessToken from facebook
     */
    public void saveIfNotExist(String id, String accessToken){
        if(!userDAO.existById(id)){
            System.out.println("\n User not exists in db, adding!");
            // Create user
            String name = graph.getName(accessToken);
            User user = new User(id,name,null);
            userDAO.save(user);

            // Create userInfo
            String email = graph.getEmail(accessToken);
            UserInfo userInfo = new UserInfo(id, email, null, null, null);
            userInfoDAO.save(userInfo);

        }
    }

    /**
     * Updates all fields in UserInfo
     * @param userInfo userInfo to update
     */
    public void updateUserInfoAll(UserInfo userInfo){

        UserInfo dbUserInfo = userInfoDAO.getById(userInfo.getUser_id());
        if (userInfo.getEmail() == dbUserInfo.getEmail()
                && userInfo.getGps_latitude() == dbUserInfo.getGps_latitude()
                && userInfo.getGps_longitude() == dbUserInfo.getGps_longitude()
                && userInfo.getDescription() == dbUserInfo.getDescription())
            return;

        userInfoDAO.updateAll(userInfo);
    }

    /**
     * Updates GPS data in UserInfo.
     * @param userId ID of user
     * @param gpsLatitude new latitude
     * @param gpsLongitude new longitude
     */
    public void updateUserInfoGps(String userId, Double gpsLatitude, Double gpsLongitude){
        userInfoDAO.updateGps(userId, gpsLatitude, gpsLongitude);
    }

    /**
     * Gets UserInfo from a specific user by ID.
     * @param id ID of user
     * @return UserInfo object
     */
    public UserInfo getUserInfoById(String id) {
        return userInfoDAO.getById(id);
    }

    /**
     * Updates Email data in UserInfo.
     * @param userId ID of user
     * @param email new email
     */
    public void updateUserInfoEmail(String userId, String email) {
        userInfoDAO.updateEmail(userId, email);
    }

    /**
     * Updates Description data in UserInfo.
     * @param userId ID of user
     * @param description new desc
     */
    public void updateUserInfoDescription(String userId, String description){
        userInfoDAO.updateDescription(userId, description);
    }

}
