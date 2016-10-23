package com.forfesten.Facebook.Controllers;

import com.forfesten.DaoWrappers.UserDAOWrapper;
import com.forfesten.Facebook.Graph;
import com.forfesten.Facebook.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Open endpoints for receiving facebook code.
 */
@RestController
public class CodeController {

    @Value(value = "${FRONTEND_URL}")
    private String FRONTEND_URL;

    @Autowired
    UserDAOWrapper userDAOWrapper;

    @Autowired
    Graph graph;

    /**
     * Here is an endpoint for facebook redirect to deliver the code.
     * The code will then be traded for a accessToken from facebook.
     * Here we also saves the user in db if not exists.
     * @param code from facebook
     * @return redirect and adds code in params
     */
    @RequestMapping(value = "/code")
    public ModelAndView codeController(@RequestParam(value = "code", defaultValue = "") String code) {

        String accessToken = graph.getAccessToken(code);
        String id = graph.authenticateToken(accessToken);

        if (accessToken != null && id != null) {

            System.out.println("\nStore AccessToken:" + accessToken);
            System.out.println("For code:" + code + "\n");

            // Add to DB if not exist
            userDAOWrapper.saveIfNotExist(id, accessToken);

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
