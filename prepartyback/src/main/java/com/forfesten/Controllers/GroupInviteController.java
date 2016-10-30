package com.forfesten.Controllers;

import com.forfesten.DaoWrappers.GroupDAOWrapper;
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

import java.util.List;
import java.util.Map;

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

        if (!requestJson.has("to_user_id")) {
            return new ResponseEntity(new ErrorJson("to_user_id does not exist.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
        }

        try {
            toUserId = requestJson.getString("to_user_id");
        } catch (JSONException e) {
            return new ResponseEntity(new ErrorJson("to_user_id must be String.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite"), HttpStatus.BAD_REQUEST);
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

    /**
     * Get all group invites for a user
     *
     * @param code usercode
     * @return group invite full data
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAllFullData(@RequestHeader(value = "Authentication") String code) {
        String userId = TokenStorage.getIdByCode(code);
        List<Object> response = groupInviteDAOWrapper.getGroupInvitesData(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Join a group if user has access to it.
     *
     * @param code       usercode
     * @param requestRaw Json of group_id
     * @return Status
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseEntity joinGroup(@RequestHeader(value = "Authentication") String code,
                                    @RequestBody String requestRaw) {

        JSONObject requestJson = new JSONObject(requestRaw);
        String userId = TokenStorage.getIdByCode(code);

        if (!requestJson.has("group_id")) {
            return new ResponseEntity(new ErrorJson("group_id is missing.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite/join"), HttpStatus.BAD_REQUEST);
        }

        int groupId = requestJson.getInt("group_id");
        try {

            groupInviteDAOWrapper.joinGroup(userId, groupId);
        } catch (Exception e) {

            if (e instanceof NoInviteException) {
                return new ResponseEntity(new ErrorJson("User does not have invitation to group.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite/join"), HttpStatus.BAD_REQUEST);
            } else if (e instanceof FullGroupException) {
                return new ResponseEntity(new ErrorJson("Group is full.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/groupinvite/join"), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
