package com.forfesten.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;


/**
 * Created by henrik on 2016-10-02.
 */

@RestController
public class Login {

    @Value(value = "FACEBOOK_APP_ID")
    String FACEBOOK_APP_ID;

    @Value(value = "FACEBOOK_APP_ID")
    String FACEBOOK_REDIRECT_URL;

    @Value(value = "FACEBOOK_EXCHANGE_KEY")
    String FACEBOOK_EXCHANGE_KEY;

    @RequestMapping(value = "/facebooklogin")
        public String getFacebookLogin(@RequestHeader(value="authorization") String authorization ) {

            return "login";
        }



    }

