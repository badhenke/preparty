package com.forfesten.Models;

import java.util.Date;

/**
 * Database model for UserInfo.
 */
public class UserInfo {

    private int id;
    private String user_id;
    private String email;
    private Double gps_latitude;
    private Double gps_longitude;
    private Date created;

    public UserInfo(String user_id, String email, Double gps_latitude, Double gps_longitude) {
        this.user_id = user_id;
        this.email = email;
        this.gps_latitude = gps_latitude;
        this.gps_longitude = gps_longitude;
    }

    public UserInfo(int id, String user_id, String email, Double gps_latitude, Double gps_longitude, Date created) {
        this.id = id;
        this.user_id = user_id;
        this.email = email;
        this.gps_latitude = gps_latitude;
        this.gps_longitude = gps_longitude;
        this.created = created;
    }

    public Double getGps_latitude() {
        return gps_latitude;
    }

    public Double getGps_longitude() {
        return gps_longitude;
    }

    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreated() {
        return created;
    }
}
