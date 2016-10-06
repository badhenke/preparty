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

            tokenMap.put(id, new AuthUser(id,code,token));
            return true;

        }else{

            if(Graph.AuthenticateToken(token) != null) {
                tokenMap.put(id, new AuthUser(id,code,token));
                return true;
            } else {
                tokenMap.remove(id);
                return false;
            }

        }

    }

}
