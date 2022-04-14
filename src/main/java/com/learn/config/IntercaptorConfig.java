package com.learn.config;

import com.learn.util.jwt.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * JWT拦截器
 */
@Configuration
public class IntercaptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                //拦截的路径
                //.addPathPatterns("/courses/**/chapter/**","/user/**","/comments/**")
                //.addPathPatterns("/courses/**/chapter/**","/comments/**")
                .addPathPatterns()

                //排除接口
                //.excludePathPatterns("/login","/register","/courses/all", "/courses/hot", "/courses/detail/**");
                .excludePathPatterns("/**");
    }
}
