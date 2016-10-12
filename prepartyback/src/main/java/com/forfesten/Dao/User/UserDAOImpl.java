package com.forfesten.Dao.User;

import com.forfesten.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by heer on 12/10/2016.
 */
public class UserDAOImpl implements UserDAO {

    private static final String SQL_INSERT = "INSERT INTO users (id, name, birthdate) values (?, ?,?) ";
    private static final String SQL_GET = "SELECT * from users ";
    private static final String SQL_DELETE_ALL = "DELETE from users ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_INSERT, user.getId(), user.getName(), user.getBirthdate());
    }

    @Override
    public User getUserById(int id) {
        String sqlquery = SQL_GET + "WHERE id=" + id;
        List<User> userList = jdbcTemplate.query(sqlquery, new UserRowMapper());
        if (userList.isEmpty()){
            return null;
        }else{
            return userList.get(0);
        }
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.execute(SQL_DELETE_ALL);
    }

    @Override
    public List<User> getAll() {
        List<User> userList = jdbcTemplate.query(SQL_GET, new UserRowMapper());
        if (userList.isEmpty()){
            return null;
        }else{
            return userList;
        }
    }

    private class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int i) throws SQLException {
            return new User(rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate"));
        }
    }

}
