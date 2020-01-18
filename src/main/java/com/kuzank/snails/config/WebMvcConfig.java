package com.kuzank.snails.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/4
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/person/account/login", "/api/file/download/**")
                .order(0);

        super.addInterceptors(registry);
    }

}

