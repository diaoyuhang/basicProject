package com.example.basicproject.config;

import com.example.basicproject.interceptor.ReqThreadInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    private ReqThreadInterceptor reqThreadInterceptor;

    @Autowired
    public void setReqThreadInterceptor(ReqThreadInterceptor reqThreadInterceptor) {
        this.reqThreadInterceptor = reqThreadInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqThreadInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/public/**","/user/login","/user/register"); // 排除特定路径
    }
}
