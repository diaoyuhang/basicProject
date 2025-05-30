package com.example.basicproject.config;

import com.example.basicproject.filter.StaticResourceFilter;
import com.example.basicproject.filter.BackendTokenFilter;
import com.example.basicproject.filter.FrontTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationBeanConfig {

    @Bean
    public FilterRegistrationBean<BackendTokenFilter> registerTokenFilter(){
        FilterRegistrationBean<BackendTokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new BackendTokenFilter());
        bean.setName("tokenFilter");
        bean.addUrlPatterns("/bk/*");
        bean.setOrder(2);
        bean.addInitParameter("excludedAccuratePaths","/bk/user/login," +
                "/bk/user/register," +
                "/bk/user/exportUserInfoExcel," +
                "/bk/user/importData");//需要排除的uri

        bean.addInitParameter("excludedVaguePaths","/file/img/.*,/test/.*,");//需要排除的uri
        return bean;
    }

    @Bean
    public FilterRegistrationBean<FrontTokenFilter> registerWXTokenFilter(){
        FilterRegistrationBean<FrontTokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new FrontTokenFilter());
        bean.setName("wXTokenFilter");
        bean.addUrlPatterns("/fr/*");
        bean.setOrder(3);
        bean.addInitParameter("excludedAccuratePaths","/fr/user/wxLogin");//需要排除的uri

        bean.addInitParameter("excludedVaguePaths","/fr/file/img/.*," +
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
