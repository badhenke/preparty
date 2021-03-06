package com.forfesten.Controllers;

import com.forfesten.DaoWrappers.UserDAOWrapper;
import com.forfesten.Facebook.TokenStorage;
import com.forfesten.Models.ErrorJson;
import com.forfesten.Models.User;
import com.forfesten.Models.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller handling user actions
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserDAOWrapper userDAOWrapper;

    /**
     * Get information about a user.
     *
     * @param userId of a user
     * @return Json of userdata
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestHeader(value = "Authentication") String code,
                                  @RequestParam(value = "id", defaultValue = "me") String userId) {

        if (userId.trim().length() <= 0) {
            return new ResponseEntity(new ErrorJson("Unknown userId.", "Bad Request", HttpStatus.BAD_REQUEST, "GET /api/user"), HttpStatus.BAD_REQUEST);
        }

        if (userId.equals("me")) {
            userId = TokenStorage.getIdByCode(code);
        }

        User user = userDAOWrapper.getUserById(userId);
        UserInfo userInfo = userDAOWrapper.getUserInfoById(userId);
        Map<String, Object> response = new HashMap<>();

        if (user == null || userInfo == null) {
            return new ResponseEntity(new ErrorJson("User does not exist.", "Bad Request", HttpStatus.BAD_REQUEST, "GET /api/user"), HttpStatus.BAD_REQUEST);
        }

        response.put("name", user.getName());
        response.put("birthdate", user.getBirthdate());
        response.put("email", userInfo.getEmail());
        response.put("gps_latitude", userInfo.getGps_latitude());
        response.put("gps_longitude", userInfo.getGps_longitude());
        response.put("group_id", user.getGroupId());
        response.put("description", userInfo.getDescription());
        response.put("created", userInfo.getCreated());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Update information about a user.
     *
     * @param requestRaw is a json input from frontend
     * @param code       Authentication header
     * @return HTTPStatus of success or not
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity updateUser(@RequestHeader(value = "Authentication") String code,
                                     @RequestBody String requestRaw) {

        JSONObject requestJson = new JSONObject(requestRaw);
        String userId = TokenStorage.getIdByCode(code);

        boolean gpsLatitudeChanged = false, gpsLongitudeChanged = false, emailChanged = false, descriptionChanged = false;
        String requestEmail = null, requestDescription = null;
        Double requestGpsLatitude = null, requestGpsLongitude = null;

        if (requestJson.has("email")) {
            requestEmail = requestJson.getString("email");
            emailChanged = true;
        }
        try {
            if (requestJson.has("gps_latitude")) {
                requestGpsLatitude = requestJson.getDouble("gps_latitude");
                gpsLatitudeChanged = true;
            }
            if (requestJson.has("gps_longitude")) {
                requestGpsLongitude = requestJson.getDouble("gps_longitude");
                gpsLongitudeChanged = true;
            }
        } catch (JSONException e) {
            return new ResponseEntity(new ErrorJson("Mood must be a valid int.", "Not Acceptable", HttpStatus.NOT_ACCEPTABLE, "PATCH /api/user"), HttpStatus.NOT_ACCEPTABLE);
        }
        if (requestJson.has("description")) {
            requestDescription = requestJson.getString("description");
            descriptionChanged = true;
        }
        if (!emailChanged && !gpsLatitudeChanged && !gpsLongitudeChanged && !descriptionChanged) {
            return new ResponseEntity(new ErrorJson("No data entered.", "Bad Request", HttpStatus.BAD_REQUEST, "PATCH /api/user"), HttpStatus.BAD_REQUEST);
        }

        if (emailChanged && gpsLatitudeChanged && gpsLongitudeChanged && descriptionChanged) {
            UserInfo userInfo = new UserInfo(userId, requestEmail, requestGpsLatitude, requestGpsLongitude, requestDescription);
            userDAOWrapper.updateUserInfoAll(userInfo);
            return new ResponseEntity(HttpStatus.OK);
        }

        if (gpsLatitudeChanged && gpsLongitudeChanged) {
            userDAOWrapper.updateUserInfoGps(userId, requestGpsLatitude, requestGpsLongitude);
        }
        if (emailChanged) {
            userDAOWrapper.updateUserInfoEmail(userId, requestEmail);
        }
        if (descriptionChanged) {
            userDAOWrapper.updateUserInfoDescription(userId, requestDescription);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


}
