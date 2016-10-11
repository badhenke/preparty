package com.forfesten.Facebook.Controllers;

import com.forfesten.Facebook.TokenStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heer on 08/10/2016.
 */
@RestController
@RequestMapping(value = "/auth")
public class ApiController {

    @RequestMapping(value = "/")
    public ResponseEntity facebookCheckController() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity facebookLogout(@RequestHeader(value = "Authentication") String code) {
        TokenStorage.Remove(code);
        return new ResponseEntity(HttpStatus.OK);
    }
}
