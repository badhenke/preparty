package com.forfesten.Dao.Mood;

import com.forfesten.Models.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of DB actions
 */
@Service
public class MoodDAOImpl implements MoodDAO {

    private static final String SQL_GET = "SELECT * FROM moods";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Mood> getAll() {
        return jdbcTemplate.query(SQL_GET, new MoodRowMapper());
    }

    private class MoodRowMapper implements RowMapper<Mood> {
        public Mood mapRow(ResultSet rs, int i) throws SQLException {
            return new Mood(rs.getInt("id"), rs.getString("name"));
        }
    }

}
