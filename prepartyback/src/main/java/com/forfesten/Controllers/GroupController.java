package com.forfesten.Controllers;

import com.forfesten.DaoWrappers.GroupDAOWrapper;
import com.forfesten.Exceptions.GroupNotExistException;
import com.forfesten.Facebook.TokenStorage;
import com.forfesten.Models.ErrorJson;
import com.forfesten.Models.Group;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller fro group endpoints.
 */
@RestController
@RequestMapping(value = "/api/group")
public class GroupController {

    @Autowired
    GroupDAOWrapper groupDAOWrapper;

    /**
     * Get a group
     *
     * @param code from user
     * @return Group if exists, Null otherwise.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getGroup(@RequestHeader(value = "Authentication") String code,
                                   @RequestParam(value = "group_id", defaultValue = "my") String groupId) {

        String userId = TokenStorage.getIdByCode(code);

        Group group = null;
        if (groupId.equals("my")) {
            group = groupDAOWrapper.getGroupForUser(userId);
        } else {
            int groupIdInt;
            try {
                groupIdInt = Integer.parseInt(groupId);
                group = groupDAOWrapper.getGroup(groupIdInt);
            } catch (Exception e) {
                if (e instanceof NumberFormatException)
                    return new ResponseEntity(new ErrorJson("groupId must be integer.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
                else if (e instanceof GroupNotExistException)
                    return new ResponseEntity(new ErrorJson("Group does not exist.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
            }
        }

        if (group == null) {
            return new ResponseEntity(new ErrorJson("Not in a group", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(group, HttpStatus.OK);
        }
    }

    /**
     * Create a new Group.
     *
     * @param code       from user.
     * @param requestRaw all data that Group needs
     * @return HTTPStatus response
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addGroup(@RequestHeader(value = "Authentication") String code,
                                   @RequestBody String requestRaw) {

        JSONObject requestJson = new JSONObject(requestRaw);
        String userId = TokenStorage.getIdByCode(code);

        String description = null;
        int moodId = -1;

        if (!requestJson.has("description") || !requestJson.has("mood_id")) {
            return new ResponseEntity(new ErrorJson("Not correct input.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
        }

        description = requestJson.getString("description");
        moodId = requestJson.getInt("mood_id");
        boolean addGroupStatus = false;

        try {
            addGroupStatus = groupDAOWrapper.saveNewGroup(userId, description, moodId);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorJson("Wrong input.", "Bad Request", HttpStatus.BAD_REQUEST, "POST /api/group"), HttpStatus.BAD_REQUEST);
        }

        if (!addGroupStatus) {
            return new ResponseEntity(new ErrorJson("User already in a group.", "Conflict", HttpStatus.CONFLICT, "POST /api/group"), HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    /**
     * Updates parameters of the group that the current user is in.
     *
     * @param code       of user.
     * @param requestRaw data to change.
     * @return HTTPStatus response.
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity changeGroupData(@RequestHeader(value = "Authentication") String code,
                                          @RequestBody String requestRaw) {

        JSONObject requestJson = new JSONObject(requestRaw);
        String userId = TokenStorage.getIdByCode(code);

        if (!requestJson.has("description") && !requestJson.has("mood_id")) {
            return new ResponseEntity(new ErrorJson("No input.", "No Content", HttpStatus.NO_CONTENT, "PATCH /api/group"), HttpStatus.NO_CONTENT);
        }

        Group group = groupDAOWrapper.getGroupForUser(userId);
        if (group == null) {
            return new ResponseEntity(new ErrorJson("Not in a group.", "Bad Request", HttpStatus.BAD_REQUEST, "PATCH /api/group"), HttpStatus.BAD_REQUEST);
        }

        String description = null;
        int moodId = -1;

        description = requestJson.getString("description");
        try {
            moodId = requestJson.getInt("mood_id");
        } catch (JSONException e) {
            return new ResponseEntity(new ErrorJson("Mood must be a valid int.", "Not Acceptable", HttpStatus.NOT_ACCEPTABLE, "PATCH /api/group"), HttpStatus.NOT_ACCEPTABLE);
        }

        group.setDescription(description);
        group.setMoodId(moodId);
        try {
            groupDAOWrapper.updateGroupAll(group);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorJson("Wrong input.", "Not Acceptable", HttpStatus.NOT_ACCEPTABLE, "PATCH /api/group"), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Drop the user from it's group. If group is empty it will be removed.
     *
     * @param code from user
     * @return Status of action
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity dropGroup(@RequestHeader(value = "Authentication") String code) {

        String userId = TokenStorage.getIdByCode(code);

        Group group = groupDAOWrapper.getGroupForUser(userId);
        if (group == null) {
            return new ResponseEntity(new ErrorJson("Not in a group.", "Bad Request", HttpStatus.BAD_REQUEST, "DELETE /api/group"), HttpStatus.BAD_REQUEST);
        }

        groupDAOWrapper.dropGroup(userId, group);

        return new ResponseEntity(HttpStatus.OK);
    }


}
