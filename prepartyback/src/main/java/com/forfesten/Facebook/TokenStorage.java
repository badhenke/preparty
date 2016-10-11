package com.forfesten.Facebook;

import com.forfesten.Facebook.Models.AuthUser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by henrik on 2016-10-05.
 */

public class TokenStorage {

    private static Map<String, AuthUser> tokenMap = new HashMap<>();

    public static boolean AddAuthUser(String id, String code, String token){

        if(!tokenMap.containsKey(id)){
            // If not exist, add it
            tokenMap.put(code, new AuthUser(id,code,token));
            return true;

        }else{
            // If exists in map
            if(Graph.AuthenticateToken(token) != null) {
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

    public static boolean Exists(String code){
        if(tokenMap.containsKey(code)){

            // If Token exists, check that it is still valid
            AuthUser user = tokenMap.get(code);
            if(Graph.AuthenticateToken(user.getToken()) != null){
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

    public static void Remove(String id){
        if(tokenMap.containsKey(id))
            tokenMap.remove(id);
    }

    public static Map<String, AuthUser> getTokenMap() {
        return tokenMap;
    }
}
