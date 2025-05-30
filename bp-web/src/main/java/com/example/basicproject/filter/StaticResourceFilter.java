package com.example.basicproject.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StaticResourceFilter implements Filter {

    private static final String BASIC_PATH = "/web/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String path = httpRequest.getRequestURI();
        if (path.startsWith(BASIC_PATH)) {
            if (path.endsWith(".js") || path.endsWith(".css")|| path.endsWith(".png")) {
                String file = path.substring(path.lastIndexOf("/"));
                httpRequest.getRequestDispatcher(file).forward(httpRequest, httpResponse);
            } else {
                httpRequest.getRequestDispatcher("/index.html").forward(httpRequest, httpResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
