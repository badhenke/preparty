package com.forfesten.Facebook.Controllers;

import com.forfesten.Facebook.TokenStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controllers regarding checking authentication and logout
 */
@RestController
@RequestMapping(value = "/auth")
public class ApiController {

    /**
     * This is a simple method to check if you are authenticated.
     * Since this is protected by Authentication filter.
     * @return HTTPStatus OK, 200
     */
    @RequestMapping(value = "/")
    public ResponseEntity facebookCheckController() {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Logs out from this system by deleting AuthUser in TokenStorage.
     * @param code to delete
     * @return HTTPStatus OK, 200
     */
    @RequestMapping(value = "/logout")
    public ResponseEntity facebookLogout(@RequestHeader(value = "Authentication") String code) {
        TokenStorage.Remove(code);
        System.out.println("Delete from token storage. TokenStorage size: " + TokenStorage.size());
        return new ResponseEntity(HttpStatus.OK);
    }
}
