package com.smartshop.configuration.interceptor;

import com.smartshop.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class Interceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CurrentUserService currentUserService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        this.currentUserService.update(null);
    }
}
