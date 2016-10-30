package com.forfesten.DaoWrappers;

import com.forfesten.Dao.Group.GroupDAOImpl;
import com.forfesten.Dao.GroupInvite.GroupInviteDAOImpl;
import com.forfesten.Dao.User.UserDAOImpl;
import com.forfesten.Exceptions.*;
import com.forfesten.Models.GroupInvite;
import com.forfesten.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for Group Invite Database interaction
 */
@Service
public class GroupInviteDAOWrapper {

    @Autowired
    GroupInviteDAOImpl groupInviteDAO;

    @Autowired
    GroupDAOImpl groupDAO;

    @Autowired
    UserDAOImpl userDAO;


    /**
     * Sends a group invite to a user and stores an invite in database.
     *
     * @param fromUserId id of sender
     * @param toUserId   if of receiver
     * @throws AlreadyInGroupException if sender is in group
     * @throws FullGroupException      if the group is full
     */
    public void sendGroupInvite(String fromUserId, String toUserId) throws
            AlreadyInGroupException,
            FullGroupException,
            NotInGroupException,
            UserNotExistException,
            AlreadyHasInviteException {

        int groupId = userDAO.getGroupId(fromUserId);

        if (groupId <= 0) {
            throw new NotInGroupException();
        }

        User toUser = userDAO.getById(toUserId);

        if (toUser == null) {
            throw new UserNotExistException();
        }

        List<User> usersInGroupList = userDAO.getAllByGroupId(groupId);
        for (User user : usersInGroupList) {
            if (user.getId().equals(toUserId)) {
                throw new AlreadyInGroupException();
            }
        }

        if (toUserId.equals(fromUserId)) {
            throw new AlreadyInGroupException();
        }

        if (usersInGroupList.size() >= 4) {
            throw new FullGroupException();
        }

        GroupInvite groupInvite = groupInviteDAO.get(toUserId, groupId);
        if (groupInvite != null) {
            throw new AlreadyHasInviteException();
        } else {
            groupInvite = new GroupInvite(toUserId, groupId);
            groupInviteDAO.save(groupInvite);
        }

    }

    /**
     * Get ready-response data of all groupinvites a user has
     * @param userId id of user
     * @return response data
     */
    public List<Object> getGroupInvitesData(String userId) {
        List<Object> dataList = groupInviteDAO.getAllFullData(userId);
        return dataList;
    }


}
