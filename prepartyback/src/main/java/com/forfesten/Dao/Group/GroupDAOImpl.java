package com.forfesten.Dao.Group;

import com.forfesten.Models.Group;
import com.mysql.cj.jdbc.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Implementation for database actions.
 */
@Service
public class GroupDAOImpl implements GroupDAO{

    private static final String SQL_INSERT = "INSERT INTO groups (description) values ('%s') ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int save(Group group){

        String sql = String.format(SQL_INSERT, group.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return (PreparedStatement) connection.prepareStatement(sql, new String[] {"id"});
                    }
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    private class UserRowMapper implements RowMapper<Group> {
        public Group mapRow(ResultSet rs, int i) throws SQLException {
            return new Group(rs.getInt("id"), rs.getString("description"));
        }
    }

}
