package com.forfesten.DaoWrappers;

import com.forfesten.Dao.Group.GroupDAOImpl;
import com.forfesten.Dao.User.UserDAOImpl;
import com.forfesten.Exceptions.GroupNotExistException;
import com.forfesten.Models.Group;
import com.forfesten.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Wrapper that should be used when doing actions to database.
 * This is because several logic can be done here before doing queries.
 */
@Service
public class GroupDAOWrapper {

    @Autowired
    GroupDAOImpl groupDAO;

    @Autowired
    UserDAOImpl userDAO;

    /**
     * Saves new Group to DB and adds group id to the user that created the group.
     * Returns true if it was added successfully. Can fail when user is already in group.
     *
     * @param id          of user that created Group
     * @param description of the group
     * @return status of the input.
     */
    public boolean saveNewGroup(String id, String description, int moodId) {

        if (userDAO.getGroupId(id) > 0) {
            return false;
        } else {
            Group group = new Group(description, moodId);
            int groupId = groupDAO.save(group);
            userDAO.setGroupId(id, groupId);
            return true;
        }
    }

    /**
     * Get group that user has joined. Null if not in a group.
     *
     * @param userId of user
     * @return Group
     */
    public Group getGroupForUser(String userId) {
        int groupId = userDAO.getGroupId(userId);
        if (groupId <= 0) {
            return null;
        } else {
            return groupDAO.getById(groupId);
        }
    }

    /**
     * Get Group by group id.
     *
     * @param groupId Id of group
     * @return Group
     * @throws GroupNotExistException If group does not exist
     */
    public Group getGroup(int groupId) throws GroupNotExistException {

        Group group = groupDAO.getById(groupId);
        if (group == null) {
            throw new GroupNotExistException();
        }

        return group;
    }

    /**
     * Update all parameters in group
     *
     * @param group to change
     */
    public void updateGroupAll(Group group) {
        groupDAO.updateAll(group);
    }

    /**
     * Drop user from Group, if it is last person then remove group.
     *
     * @param userId of user
     * @param group  object of Group
     */
    public void dropGroup(String userId, Group group) {
        userDAO.setGroupNull(userId);

        List<User> userList = userDAO.getAllByGroupId(group.getId());
        if (userList.isEmpty()) {
            groupDAO.deleteGroup(group.getId());
        }
    }
}
