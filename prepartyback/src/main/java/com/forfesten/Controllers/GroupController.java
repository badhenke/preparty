package com.forfesten.Controllers;

import com.forfesten.DaoWrappers.GroupDAOWrapper;
import com.forfesten.Facebook.TokenStorage;
import com.forfesten.Models.ErrorJson;
import com.forfesten.Models.Group;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by heer on 08/10/2016.
 */
@RestController
@RequestMapping(value = "/api/group")
public class GroupController {

    @Autowired
    GroupDAOWrapper groupDAOWrapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getGroup(@RequestHeader(value = "Authentication") String code) {

        String userId = TokenStorage.getIdByCode(code);
        Group group = groupDAOWrapper.getGroup(userId);

        if (group == null) {
            return new ResponseEntity(new ErrorJson("Not in a group", "Bad Request", HttpStatus.NO_CONTENT, "POST /api/group"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(group, HttpStatus.OK);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addGroup(@RequestHeader(value = "Authentication") String code,
                                   @RequestBody String requestRaw) {

        JSONObject requestJson = new JSONObject(requestRaw);
        String userId = TokenStorage.getIdByCode(code);

        String description = null;
        int moodId=-1;

        if (!requestJson.has("description") || !requestJson.has("moodId")) {
            return new ResponseEntity(new ErrorJson("Not correct input.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
        }

        description = requestJson.getString("description");
        moodId = requestJson.getInt("moodId");
        boolean addGroupStatus = false;

        try {
            addGroupStatus = groupDAOWrapper.saveNewGroup(userId, description, moodId);
        }catch (Exception e){
            return new ResponseEntity(new ErrorJson("Wrong input.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
        }

        if (!addGroupStatus) {
            return new ResponseEntity(new ErrorJson("User already in a group.", "Conflict", HttpStatus.CONFLICT, "POST /api/group"), HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity changeGroupData(@RequestHeader(value = "Authentication") String code,
                                   @RequestBody String requestRaw) {

        JSONObject requestJson = new JSONObject(requestRaw);
        String userId = TokenStorage.getIdByCode(code);

        if (!requestJson.has("description") && !requestJson.has("moodId")) {
            return new ResponseEntity(new ErrorJson("No input.", "No Content", HttpStatus.NO_CONTENT, "PATCH /api/group"), HttpStatus.NO_CONTENT);
        }

        Group group = groupDAOWrapper.getGroup(userId);
        if(group == null){
            return new ResponseEntity(new ErrorJson("Not in a group.", "Bad Request", HttpStatus.BAD_REQUEST, "PATCH /api/group"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup() {
        return null;
    }


}
