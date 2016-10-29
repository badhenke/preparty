package com.forfesten.Dao.UserInfo;

import com.forfesten.Models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation for database actions.
 */
@Service
public class UserInfoDAOImpl implements UserInfoDAO{

    private static final String SQL_INSERT = "INSERT INTO userinfos (user_id, email, gps_latitude, gps_longitude, description) values (?, ?, ?, ?, ?) ";
    private static final String SQL_GET = "SELECT * from userinfos ";
    private static final String SQL_UPDATE = "UPDATE userinfos SET email=?, gps_latitude=?, gps_longitude=?, description=? where user_id=?";
    private static final String SQL_UPDATE_GPS = "UPDATE userinfos SET gps_latitude=?, gps_longitude=? where user_id=?";
    private static final String SQL_UPDATE_EMAIL = "UPDATE userinfos SET email=? where user_id=?";
    private static final String SQL_UPDATE_DESCRIPTION = "UPDATE userinfos SET description=? where user_id=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserInfo userInfo) {
        jdbcTemplate.update(SQL_INSERT,userInfo.getUser_id(), userInfo.getEmail(), userInfo.getGps_latitude(), userInfo.getGps_longitude(), userInfo.getDescription());
    }

    @Override
    public UserInfo getById(String id){
        String sql = SQL_GET + " WHERE user_id="+id;
        List<UserInfo> userInfoList =  jdbcTemplate.query(sql,new UserInfoRowMapper());
        if(userInfoList.isEmpty()){
            return null;
        }else{
            return userInfoList.get(0);
        }
    }

    @Override
    public void updateAll(UserInfo userInfo){
        jdbcTemplate.update(SQL_UPDATE, userInfo.getEmail(), userInfo.getGps_latitude(), userInfo.getGps_longitude(), userInfo.getDescription(), userInfo.getUser_id());
    }

    @Override
    public void updateGps(String userId, Double gpsLatitude, Double gpsLongitude){
        jdbcTemplate.update(SQL_UPDATE_GPS, gpsLatitude, gpsLongitude, userId);
    }

    @Override
    public void updateEmail(String userId, String email) {
        jdbcTemplate.update(SQL_UPDATE_EMAIL, email, userId);
    }

    @Override
    public void updateDescription(String userId, String description){
        jdbcTemplate.update(SQL_UPDATE_DESCRIPTION, description, userId);
    }

    private class UserInfoRowMapper implements RowMapper<UserInfo> {
        public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
            return new UserInfo(rs.getInt("id"), rs.getString("user_id"), rs.getString("email") , rs.getDouble("gps_latitude"), rs.getDouble("gps_longitude"), rs.getDate("created"), rs.getString("description"));
        }
    }
}
