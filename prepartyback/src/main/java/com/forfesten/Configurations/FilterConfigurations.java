package com.forfesten.Configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by heer on 07/10/2016.
 */
@Configuration
public class FilterConfigurations {

    /**
     * Adding a CORS filter to the chain.
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(-100);
        return bean;
    }

    /**
     * Setup for authentication filter bean.
     */
    @Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new AuthenticationFilter());
        bean.addUrlPatterns("/api/*");
        bean.addUrlPatterns("/auth/*");
        bean.setOrder(-50);
        return bean;
    }


}
