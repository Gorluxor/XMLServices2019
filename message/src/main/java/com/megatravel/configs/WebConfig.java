package com.megatravel.configs;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Component
@Configuration
public class WebConfig implements ServletContextInitializer {

    private static WebApplicationContext webApplicationContext;

    public static WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public static void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        WebConfig.webApplicationContext = webApplicationContext;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }
}
