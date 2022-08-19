package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.logininterceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

/*
        //在這個patterns裡面就是不用登入的
        List<String> patterns = new ArrayList<>();
        patterns.add("/user_login");
        patterns.add("/static/**");
*/

      //有些之後會用到
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/API/**");

        //.excludePathPatterns(patterns);
    }
}