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
    private String description;

    public UserInfo(String user_id, String email, Double gps_latitude, Double gps_longitude, String description) {
        this.user_id = user_id;
        this.email = email;
        this.gps_latitude = gps_latitude;
        this.gps_longitude = gps_longitude;
        this.description = description;
    }

    public UserInfo(int id, String user_id, String email, Double gps_latitude, Double gps_longitude, Date created, String description) {
        this.id = id;
        this.user_id = user_id;
        this.email = email;
        this.gps_latitude = gps_latitude;
        this.gps_longitude = gps_longitude;
        this.created = created;
        this.description = description;
    }

    public String getDescription() {
        return description;
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
