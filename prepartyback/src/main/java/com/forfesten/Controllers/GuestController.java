package com.forfesten.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heer on 09/10/2016.
 */
@RestController
@RequestMapping(value = "/api/guest")
public class GuestController {

    @RequestMapping(value = "/all")
    public ResponseEntity getAllGroups() {
        return null;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getGroup() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addGroup() {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup() {
        return null;
    }

}
