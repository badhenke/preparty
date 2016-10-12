package com.forfesten.Dao.User;

import com.forfesten.Models.User;

import java.util.List;

/**
 * Created by heer on 12/10/2016.
 */
public interface UserDAO {

    public void save(User user);

    public List<User> getAll();

    public void deleteAll();

    public User getUserById(int id);

}
