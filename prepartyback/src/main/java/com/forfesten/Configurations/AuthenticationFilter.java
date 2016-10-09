package com.forfesten.Configurations;

import com.forfesten.Facebook.TokenStorage;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String auth = ((HttpServletRequest) request).getHeader("Authentication");
        if (auth == null) {
            ((HttpServletResponse) response).setStatus(400);
            System.out.println("Authentication = null");
            return;
        } else {
            if (!TokenStorage.Exists(auth)) {

                ((HttpServletResponse) response).setStatus(400);
                System.out.println("Authentication does not exist in tokenstorage");
                return;
            }
        }

        System.out.println("Continue");
        chain.doFilter(request, response);
    }
}
