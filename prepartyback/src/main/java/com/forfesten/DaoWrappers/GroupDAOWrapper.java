package com.forfesten.DaoWrappers;

import com.forfesten.Dao.Group.GroupDAOImpl;
import com.forfesten.Dao.User.UserDAOImpl;
import com.forfesten.Models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Group getGroup(String id) {
        int groupId = userDAO.getGroupId(id);
        if (groupId <= 0) {
            return null;
        } else {
            return groupDAO.getById(groupId);
        }
    }

}
