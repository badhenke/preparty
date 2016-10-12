package com.forfesten.Dao.User;

import com.forfesten.DatabaseConfiguration;
import com.forfesten.Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by henrik on 2016-10-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes={DatabaseConfiguration.class, UserDAOImpl.class})
public class UserDAOImplTest {

    @Autowired
    UserDAOImpl userDAO;
    private static int last_i;

    @Test
    public void deleteFirst(){

        userDAO.deleteAll();
    }

    @Test
    public void getAllFirst() throws Exception {
        List<User> userList = userDAO.getAll();
        assertNull(userList);
    }

    @Test
    public void save() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2016-10-12";
        userDAO.save(new User(1234,"Test Testson", sdf.parse(dateInString)));
    }

    @Test
    public void getAll() throws Exception {
        List<User> userList = userDAO.getAll();
        assertEquals(userList.size(),1);
    }

}