package com.forfesten.Facebook.Controllers;

import com.forfesten.Dao.User.UserDAOImpl;
import com.forfesten.Facebook.Graph;
import com.forfesten.Facebook.TokenStorage;
import com.forfesten.Models.User;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by henrik on 2016-10-02.
 */
@RestController
public class CodeController {

    @Value(value = "${FRONTEND_URL}")
    private String FRONTEND_URL;

    @Autowired
    UserDAOImpl userDAO;

    @Autowired
    Graph graph;

    @RequestMapping(value = "/code")
    public ModelAndView codeController(@RequestParam(value = "code", defaultValue = "") String code) {

        String accessToken = graph.getAccessToken(code);
        String id = graph.authenticateToken(accessToken);

        if (accessToken != null && id != null) {

            System.out.println("Store AccessToken:" + accessToken);

            // Add to DB if not exist
            userDAO.saveIfNotExist(id, accessToken);

            // Add to token storage
            TokenStorage.AddAuthUser(id, code, accessToken);

            ModelMap model = new ModelMap();
            model.put("code", code);
            return new ModelAndView("redirect:" + FRONTEND_URL, model);

        } else {
            System.out.println("Authentication error");
            return new ModelAndView("redirect:" + FRONTEND_URL);
        }
    }


}
