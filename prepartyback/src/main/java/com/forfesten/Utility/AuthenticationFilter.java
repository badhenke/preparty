package com.forfesten.Utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.UrlUtils;
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

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String currentUrl = UrlUtils.buildRequestUrl((HttpServletRequest) request);
            Usuario usuario=(Usuario) authentication.getPrincipal();
            if("/activacion".equals(currentUrl) || "/configuracion_modelo".equals(currentUrl)) {
                chain.doFilter(request, response);
                return;
            } else if (usuario.getActivationKey()!=null) {
                ((HttpServletResponse) response).sendRedirect("/activacion");
                return;
            } else if (authentication.getAuthorities().contains(AppRole.NUEVO_USUARIO)) {
                ((HttpServletResponse)response).sendRedirect("/configuracion_modelo");
                return;
            }
        }*/
        System.out.println(((HttpServletRequest) request).getHeaderNames());
        ((HttpServletResponse)response).sendRedirect("/fail");
        return;

        //chain.doFilter(request, response);
    }
}