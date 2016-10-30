package com.forfesten.Dao.GroupInvite;

import com.forfesten.Models.GroupInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of Group Invite Database Interaction
 */
@Service
public class GroupInviteDAOImpl implements GroupInviteDAO {

    private static final String SQL_INSERT = "INSERT INTO groupinvites (user_id, group_id) values (?, ?) ";
    private static final String SQL_GET = "SELECT * FROM groupinvites ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(GroupInvite groupInvite) {
        jdbcTemplate.update(SQL_INSERT, groupInvite.getUserId(), groupInvite.getGroupId());
    }

    @Override
    public GroupInvite getGroupInvite(String userId, int groupId) {
        String sql = SQL_GET + " WHERE user_id=" + userId + " AND group_id=" + groupId;
        List<GroupInvite> groupInvitesList = jdbcTemplate.query(sql, new GroupInviteRowMapper());
        if (groupInvitesList.isEmpty()) {
            return null;
        } else {
            return groupInvitesList.get(0);
        }
    }

    private class GroupInviteRowMapper implements RowMapper<GroupInvite> {
        public GroupInvite mapRow(ResultSet rs, int i) throws SQLException {
            return new GroupInvite(rs.getInt("id"), rs.getString("user_id"), rs.getInt("group_id"), rs.getDate("created"));
        }
    }

}
