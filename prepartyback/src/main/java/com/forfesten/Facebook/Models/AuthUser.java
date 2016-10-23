package com.forfesten.Facebook.Models;

/**
 * Special User Object that is used in TokenStorage.
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
