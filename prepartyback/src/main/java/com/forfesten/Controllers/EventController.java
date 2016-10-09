package com.forfesten.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heer on 08/10/2016.
 */
@RestController
@RequestMapping(value = "/api/event")
public class EventController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addEvent(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getEvent(){
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteEvent(){
        return null;
    }

}
