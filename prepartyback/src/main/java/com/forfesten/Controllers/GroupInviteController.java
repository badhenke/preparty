package com.forfesten.Controllers;

import com.forfesten.DaoWrappers.GroupInviteDAOWrapper;
import com.forfesten.Exceptions.*;
import com.forfesten.Facebook.TokenStorage;
import com.forfesten.Models.ErrorJson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Group Invites
 */
@RestController
@RequestMapping(value = "/api/groupinvite")
public class GroupInviteController {

    @Autowired
    GroupInviteDAOWrapper groupInviteDAOWrapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createInvite(@RequestHeader(value = "Authentication") String code,
                                       @RequestBody String requestRaw) {

        JSONObject requestJson = new JSONObject(requestRaw);
        String userId = TokenStorage.getIdByCode(code);
        String toUserId = null;

        if (!requestJson.has("toUserId")) {
            return new ResponseEntity(new ErrorJson("toUserId does not exist.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
        }

        try {
            toUserId = requestJson.getString("toUserId") ;
        } catch (JSONException e) {
            return new ResponseEntity(new ErrorJson("toUserId must be String.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
        }

        try {
            groupInviteDAOWrapper.sendGroupInvite(userId, toUserId);
        } catch (Exception e) {

            if (e instanceof NotInGroupException) {
                return new ResponseEntity(new ErrorJson("Not in a group.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
            } else if (e instanceof UserNotExistException) {
                return new ResponseEntity(new ErrorJson("User to receive not found.", "Not Found", HttpStatus.NOT_FOUND, "POST /api/groupinvite"), HttpStatus.NOT_FOUND);
            } else if (e instanceof AlreadyInGroupException) {
                return new ResponseEntity(new ErrorJson("User already in group.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
            } else if (e instanceof FullGroupException) {
                return new ResponseEntity(new ErrorJson("Group is full.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
            } else if (e instanceof AlreadyHasInviteException) {
                return new ResponseEntity(new ErrorJson("Group Invite already exist to this group.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
            }

        }

        return new ResponseEntity(HttpStatus.CREATED);

    }

}
