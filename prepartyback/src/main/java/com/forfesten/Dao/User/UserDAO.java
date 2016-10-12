package com.forfesten.Dao.User;

import com.forfesten.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by heer on 12/10/2016.
 */
public interface UserDAO {

    public void save(User user);



}
