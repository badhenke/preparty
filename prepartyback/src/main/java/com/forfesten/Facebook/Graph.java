package com.forfesten.Facebook;

/**
 * Created by henrik on 2016-10-02.
 */

import com.forfesten.HttpHelpers.Request;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Graph {



    public static String GetAccessToken(String code) {

        String url = "https://graph.facebook.com/oauth/access_token?" +
                "client_id=" + "992787620844475" +
                "&redirect_uri=" +"http://localhost:8080/auth-web/code"+
                "&client_secret=c13b3bacd37e2cd18532d4ba60a94ba7" +
                "&code="+ code;

        try {

            String result = Request.Get(url);
            String accessToken = result.substring(13);
            return accessToken;

        } catch (Exception e) {
            String errormsg = "Graph.Authenticate Error:" + e.getMessage();
            System.out.println(errormsg);

            return null;
        }
    }

    public static String AuthenticateToken (String token) {

        String url = "https://graph.facebook.com/me?access_token=" + token;
        try {

            String result = Request.Get(url);
            JSONObject obj = new JSONObject(result);
            return obj.getString("id");

        }catch (Exception e){
            return null;
        }
    }


}
