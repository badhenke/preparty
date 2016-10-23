package com.forfesten.Facebook;

import com.forfesten.Facebook.Models.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Place to storetokens
 */

public class TokenStorage {

    private static Graph graph = new Graph();

    private static Map<String, AuthUser> tokenMap = new HashMap<>();

    /**
     * Adds a new user in TokenStorage
     * @param id of user
     * @param code of user
     * @param token accesstoken of user
     * @return a status
     */
    public static boolean AddAuthUser(String id, String code, String token){

        if(!tokenMap.containsKey(code)){
            // If not exist, add it
            tokenMap.put(code, new AuthUser(id,code,token));
            return true;

        }else{
            // If exists in map
            if(graph.authenticateToken(token) != null) {
                // It it is a correct token, add it into map
                tokenMap.put(code, new AuthUser(id,code,token));
                return true;
            } else {
                // If its not a valid token, return false
                tokenMap.remove(code);
                return false;
            }
        }
    }

    /**
     * Check if it exists in TokenStorage and if it is valid by facebook.
     * @param code from user
     * @return a status
     */
    public static boolean Exists(String code){
        if(tokenMap.containsKey(code)){

            // If Token exists, check that it is still valid
            AuthUser user = tokenMap.get(code);
            if(graph.authenticateToken(user.getToken()) != null){
                // Token valid, return true
                return true;
            }else{
                // Token not valid, return false
                return false;
            }
        }else {
            // Token does not exist in map, return false
            return false;
        }
    }

    /**
     * Deletes a AuthUser from TokenStorage by code.
     * @param code
     */
    public static void Remove(String code){
        if(tokenMap.containsKey(code))
            tokenMap.remove(code);
    }

    /**
     * Get ID by code.
     * @param code to check
     * @return ID, null if not exists
     */
    public static String getIdByCode(String code){
        if(tokenMap.containsKey(code))
            return tokenMap.get(code).getId();
        else
            return null;
    }

}
