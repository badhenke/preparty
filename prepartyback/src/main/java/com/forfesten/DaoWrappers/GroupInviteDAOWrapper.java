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
     * Sends a group invitation to a user from a user.
     *
     * @param fromUserId from id
     * @param toUserId   to id
     * @throws AlreadyInGroupException   If user already in group
     * @throws FullGroupException        If group is full
     * @throws NotInGroupException       If user is not in a group
     * @throws UserNotExistException     If the toUser does not exist
     * @throws AlreadyHasInviteException If user already has the same invitation
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

        if (usersInGroupList.size() > 4) {
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
     *
     * @param userId id of user
     * @return response data
     */
    public List<Object> getGroupInvitesData(String userId) {

        List<Object> dataList = groupInviteDAO.getAllFullData(userId);
        return dataList;
    }

    /**
     * Join a group if invited. If already in a group and is the last one there, the old group will be deleted.
     *
     * @param userId  id of user
     * @param groupId id of group
     * @throws NoInviteException  If user does not have an invite
     * @throws FullGroupException If the group is full
     */
    public void joinGroup(String userId, int groupId) throws NoInviteException, FullGroupException {

        GroupInvite groupInvite = groupInviteDAO.get(userId, groupId);
        if (groupInvite == null) {
            throw new NoInviteException();
        }

        List<User> usersInGroupList = userDAO.getAllByGroupId(groupId);
        if (usersInGroupList.size() > 4) {
            throw new FullGroupException();
        }

        int oldGroupId = userDAO.getGroupId(userId);

        groupInviteDAO.deleteInvite(userId, groupId);
        userDAO.setGroupId(userId, groupId);

        List<User> userList = userDAO.getAllByGroupId(oldGroupId);
        if (userList.isEmpty()) {
            groupDAO.deleteGroup(oldGroupId);
        }

    }
}
