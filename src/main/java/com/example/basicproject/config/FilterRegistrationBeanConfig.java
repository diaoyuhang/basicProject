package com.example.basicproject.config;

import com.example.basicproject.filter.StaticResourceFilter;
import com.example.basicproject.filter.TokenFilter;
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
        bean.addInitParameter("excludedAccuratePaths","");//需要排除的uri
        bean.addInitParameter("excludedVaguePaths","");//需要排除的uri
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
