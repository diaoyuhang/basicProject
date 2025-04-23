package com.example.basicproject.config;

import com.example.basicproject.filter.StaticResourceFilter;
import com.example.basicproject.filter.TokenFilter;
import com.example.basicproject.filter.WXTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationBeanConfig {

    @Bean
    public FilterRegistrationBean<TokenFilter> registerTokenFilter(){
        FilterRegistrationBean<TokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TokenFilter());
        bean.setName("tokenFilter");
        bean.addUrlPatterns("/*");
        bean.setOrder(2);
        bean.addInitParameter("excludedAccuratePaths","/user/login," +
                "/user/register," +
                "/user/exportUserInfoExcel," +
                "/user/importData");//需要排除的uri

        bean.addInitParameter("excludedVaguePaths","/file/img/.*," +
                "/test/.*," +
                "/wx/.*");//需要排除的uri
        return bean;
    }

    @Bean
    public FilterRegistrationBean<WXTokenFilter> registerWXTokenFilter(){
        FilterRegistrationBean<WXTokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WXTokenFilter());
        bean.setName("wXTokenFilter");
        bean.addUrlPatterns("/wx/*");
        bean.setOrder(3);
        bean.addInitParameter("excludedAccuratePaths","/wx/user/wxLogin");//需要排除的uri

        bean.addInitParameter("excludedVaguePaths","/file/img/.*," +
                "/test/.*");//需要排除的uri
        return bean;
    }

    @Bean
    public FilterRegistrationBean<StaticResourceFilter> registerStaticResourceFilter(){
        FilterRegistrationBean<StaticResourceFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new StaticResourceFilter());
        bean.setName("staticResourceFilter");
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
}
