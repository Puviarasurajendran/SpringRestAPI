package com.zoho.spring.versionhandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ApiVersionInterceptor implements HandlerInterceptor{

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String version = getVersionFromPath(requestURI);
        String handlerType = getHandlerTypeFromPath(requestURI);
        String pojoName="com.zoho.spring.handler."+version+".Customer"+version;
        String beanName = handlerType + version;
        System.out.println("Inside ApiVersionInterceptor <><><><><>>>"+beanName);

        request.setAttribute("handler", beanName); // Set handler attribute in request
        request.setAttribute("pojo", pojoName); 
        return true; // Continue processing the request
    }

    private String getVersionFromPath(String requestURI) {
        if (requestURI.contains("/api/v1/")) {
            return "v1";
        } else if (requestURI.contains("/api/v2/")) {
            return "v2";
        } else {
            return "v2"; // Default to v1
        }
    }

    private String getHandlerTypeFromPath(String requestURI) {

        if (requestURI.contains("/product")) {
            return "productHandler";
        } else if (requestURI.contains("/customer")) {
            return "customerHandler";
        } else if (requestURI.contains("/vendor")) {
            return "vendorHandler";
        } else {

            return "customerHandler"; // Default to customerHandler
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Post-handle logic, if needed
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // After completion logic, if needed
    }
}
