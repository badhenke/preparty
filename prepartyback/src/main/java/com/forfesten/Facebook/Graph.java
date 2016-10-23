package com.forfesten.Facebook;

/**
 * HTTP requests for connecting to Facebook Graph API.
 */

import com.forfesten.HttpHelpers.Request;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Service
public class Graph {


    /**
     * Trades a code to accessToken.
     * @param code to send
     * @return accessToken from facebook
     */
    public String getAccessToken(String code) {

        String url = "https://graph.facebook.com/oauth/access_token?" +
                "client_id=" + "992787620844475" +
                "&redirect_uri=" +"http://localhost:8080/code"+
                "&client_secret=c13b3bacd37e2cd18532d4ba60a94ba7" +
                "&code="+ code;

        try {
            String result = Request.Get(url);
            String accessToken = result.substring(13);
            return accessToken;

        } catch (Exception e) {
            String errormsg = "Graph.Authenticate ErrorJson:" + e.getMessage();
            System.out.println(errormsg);
            return null;
        }
    }

    /**
     * Checks if Access token is valid.
     * @param token to check
     * @return id of user or Null if invalid
     */
    public String authenticateToken (String token) {

        String url = "https://graph.facebook.com/me?access_token=" + token;
        try {

            String result = Request.Get(url);
            JSONObject obj = new JSONObject(result);
            return obj.getString("id");

        }catch (Exception e){
            return null;
        }
    }

    /**
     * Gets the profile picture.
     * @param token accesstoken
     * @return url to picture
     */
    public String getProfilePictureUrl(String token){
        String url = "https://graph.facebook.com/me?fields=picture&type=large&access_token="+token;
        try {

            String result = Request.Get(url);
            JSONObject obj = new JSONObject(result);
            return obj.getJSONObject("picture").getJSONObject("data").getString("url");

        }catch (Exception e){
            return null;
        }
    }

    /**
     * Gets name of user.
     * @param token accessToken
     * @return name
     */
    public String getName(String token){
        String url = "https://graph.facebook.com/me?fields=name&type=large&access_token="+token;
        try {

            String result = Request.Get(url);
            JSONObject obj = new JSONObject(result);
            return obj.getString("name");
        }catch (Exception e){
            return null;
        }
    }

    public String getEmail(String token){
        String url = "https://graph.facebook.com/me?fields=email&type=large&access_token="+token;
        try {
            String result = Request.Get(url);
            JSONObject obj = new JSONObject(result);
            return obj.getString("email");
        }catch (Exception e){
            return null;
        }
    }

}
