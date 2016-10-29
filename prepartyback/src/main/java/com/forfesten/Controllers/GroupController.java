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
            return new ResponseEntity(HttpStatus.NO_CONTENT);
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

        if (!requestJson.has("description")) {
            return new ResponseEntity(new ErrorJson("No data entered.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
        }

        description = requestJson.getString("description");

        boolean addGroupStatus = groupDAOWrapper.saveNewGroup(userId, description);

        if (!addGroupStatus) {
            return new ResponseEntity(new ErrorJson("User already in a group.", "Bad Request", HttpStatus.CONFLICT, "POST /api/group"), HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(HttpStatus.CREATED);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup() {
        return null;
    }


}
