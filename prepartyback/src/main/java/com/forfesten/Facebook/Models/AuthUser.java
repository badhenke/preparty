package com.forfesten.Facebook.Models;

/**
 * Created by henrik on 2016-10-05.
 */
public class AuthUser {
    private String id;
    private String code;
    private String token;

    public AuthUser(String id, String code, String token) {
        this.id = id;
        this.code = code;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getToken() {
        return token;
    }
}
