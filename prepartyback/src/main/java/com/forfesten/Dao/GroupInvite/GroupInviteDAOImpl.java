package com.forfesten.Dao.GroupInvite;

import com.forfesten.Models.GroupInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Group Invite Database Interaction
 */
@Service
public class GroupInviteDAOImpl implements GroupInviteDAO {

    private static final String SQL_INSERT = "INSERT INTO groupinvites (user_id, group_id) values (?, ?) ";
    private static final String SQL_GET = "SELECT * FROM groupinvites ";
    private static final String SQL_GET_FULLDATA = "SELECT groupinvites.id, user_id, group_id, groups.description, moods.name as mood " +
            "FROM groupinvites " +
            "INNER JOIN groups " +
            "ON groups.id = groupinvites.group_id " +
            "INNER JOIN moods " +
            "ON moods.id = groups.mood_id ";
    private static final String SQL_DELETE = "DELETE FROM groupinvites WHERE user_id=? AND group_id=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(GroupInvite groupInvite) {
        jdbcTemplate.update(SQL_INSERT, groupInvite.getUserId(), groupInvite.getGroupId());
    }

    @Override
    public GroupInvite get(String userId, int groupId) {
        String sql = SQL_GET + " WHERE user_id=" + userId + " AND group_id=" + groupId;
        List<GroupInvite> groupInvitesList = jdbcTemplate.query(sql, new GroupInviteRowMapper());
        if (groupInvitesList.isEmpty()) {
            return null;
        } else {
            return groupInvitesList.get(0);
        }
    }

    @Override
    public List<GroupInvite> getAll(String userId) {
        String sql = SQL_GET + " WHERE user_id=" + userId;
        return jdbcTemplate.query(sql, new GroupInviteRowMapper());
    }

    @Override
    public List<Object> getAllFullData(String userId) {
        String sql = SQL_GET_FULLDATA + " WHERE user_id=" + userId;
        List<Object> groupInviteDataList = jdbcTemplate.query(sql, new GroupInviteFullRowMapper());
        return groupInviteDataList;
    }

    @Override
    public void deleteInvite(String userId, int groupId) {
        jdbcTemplate.update(SQL_DELETE, userId, groupId);
    }

    private class GroupInviteRowMapper implements RowMapper<GroupInvite> {
        public GroupInvite mapRow(ResultSet rs, int i) throws SQLException {
            return new GroupInvite(rs.getInt("id"), rs.getString("user_id"), rs.getInt("group_id"), rs.getDate("created"));
        }
    }

    private class GroupInviteFullRowMapper implements RowMapper<Object> {
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            Map<String, Object> data = new HashMap<>();
            data.put("id", rs.getInt("id"));
            data.put("user_id", rs.getString("user_id"));
            data.put("group_id", rs.getInt("group_id"));
            data.put("description", rs.getString("description"));
            data.put("mood", rs.getString("mood"));
            return data;
        }
    }

}
