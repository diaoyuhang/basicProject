package com.example.basicproject.config;

import com.example.basicproject.interceptor.ReqThreadInterceptor;
import com.example.basicproject.interceptor.CoreReqThreadInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    private ReqThreadInterceptor reqThreadInterceptor;

    private CoreReqThreadInterceptor coreReqThreadInterceptor;

    @Autowired
    public void setWxReqThreadInterceptor(CoreReqThreadInterceptor coreReqThreadInterceptor) {
        this.coreReqThreadInterceptor = coreReqThreadInterceptor;
    }

    @Autowired
    public void setReqThreadInterceptor(ReqThreadInterceptor reqThreadInterceptor) {
        this.reqThreadInterceptor = reqThreadInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqThreadInterceptor)
                .addPathPatterns("/bk/**") // 拦截所有路径
                .excludePathPatterns("/public/**","/bk/user/login","/bk/user/register"); // 排除特定路径

        registry.addInterceptor(coreReqThreadInterceptor)
                .addPathPatterns("/fr/**")
                .excludePathPatterns("/wx/user/login");
    }
}
