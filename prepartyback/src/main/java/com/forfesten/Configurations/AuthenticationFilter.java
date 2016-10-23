package com.forfesten.Configurations;

import com.forfesten.Facebook.TokenStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    private static String redirect = "https://www.facebook.com/dialog/oauth/?client_id=992787620844475&redirect_uri=http://localhost:8080/code&display=page&response_type=code";

    /**
     * Checks if Authentication header exists and is valid, if not,
     * send a redirect to facebook login page.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String auth = ((HttpServletRequest) request).getHeader("Authentication");
        if (auth == null) {
            //((HttpServletResponse) response).setStatus(401);

            ((HttpServletResponse) response).sendRedirect(redirect);
            System.out.println("Authentication = null");
            return;
        } else {
            if (!TokenStorage.Exists(auth)) {

                ((HttpServletResponse) response).sendRedirect(redirect);

                System.out.println("Authentication does not exist in tokenstorage");
                return;
            }
        }

        System.out.println("Authenticated user - Continue");
        chain.doFilter(request, response);
    }
}
