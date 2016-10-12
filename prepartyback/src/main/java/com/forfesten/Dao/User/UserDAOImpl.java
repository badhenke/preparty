package com.forfesten.Dao.User;

import com.forfesten.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by heer on 12/10/2016.
 */
public class UserDAOImpl implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {

    }
}
