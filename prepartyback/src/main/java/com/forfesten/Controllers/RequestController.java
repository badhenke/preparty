package com.forfesten.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heer on 09/10/2016.
 */
@RestController
@RequestMapping(value = "/api/request")
public class RequestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getRequest(){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity makeRequest(){
        return null;
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteRequest(){
        return null;
    }

}
