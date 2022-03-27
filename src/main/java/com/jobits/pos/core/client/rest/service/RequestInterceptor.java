/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.service;

import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.core.module.PosCoreModule;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class RequestInterceptor implements AsyncHandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        var token = getToken(request);
        if (token == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().append("Token not present in the Request Header");
            return false;
        }
        setCurrentUser(token);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView); //To change body of generated methods, choose Tools | Templates.
        CoreUserResolver.getInstance().clearToken();
    }

    private String getToken(HttpServletRequest request) {
        System.out.println("In preHandle we are Intercepting the Request");
        System.out.println("____________________________________________");
        String requestURI = request.getRequestURI();
        String token = request.getHeader("Authorization");
        System.out.println("RequestURI::" + requestURI + " || Search for Token  :: " + token);
        System.out.println("____________________________________________");
        return token.replace("Bearer ", "");

    }

    private void setCurrentUser(String token) {
        CoreUserResolver.getInstance().resolveCurrentToken(token);
        ConfiguracionService service = PosCoreModule.getInstance().getImplementation(ConfiguracionService.class);
        service.cargarConfiguracion();
    }

}
