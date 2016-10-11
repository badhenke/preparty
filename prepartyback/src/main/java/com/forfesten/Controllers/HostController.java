package com.forfesten.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heer on 09/10/2016.
 */
@RestController
@RequestMapping(value = "/api/host")
public class HostController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createHost(){
        return null;
    }

}
