package com.forfesten.Controllers;

import com.forfesten.HttpHelpers.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heer on 09/10/2016.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUser(){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(){
        return null;
    }



}
