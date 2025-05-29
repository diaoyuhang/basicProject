package com.example.basicproject.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.constant.BaseConstant;
import com.example.basicproject.dto.user.UserTokenInfo;
import com.example.basicproject.utils.ReqThreadInfoUtil;
import com.example.basicproject.utils.SecretUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class BackendTokenFilter implements Filter {

    private Set<String> excludedAccuratePaths;

    private Set<Pattern> excludedVaguePaths;

    @Override
    public void init(FilterConfig filterConfig) {
        String paths = filterConfig.getInitParameter("excludedAccuratePaths");
        excludedAccuratePaths = new HashSet<>(Arrays.asList(paths.split(BaseConstant.COMMA_SEPARATOR)));

        String vaguePaths = filterConfig.getInitParameter("excludedVaguePaths");
        excludedVaguePaths = new HashSet<>();
        for (String path : vaguePaths.split(BaseConstant.COMMA_SEPARATOR)) {
            excludedVaguePaths.add(Pattern.compile(path));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        try {
            String token = request.getHeader("token");
            if (!isAuthWhiteList(requestURI)) {
                if (StringUtils.isBlank(token)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    return;
                } else {
                    String decrypt = SecretUtil.decrypt(token);
                    UserTokenInfo userTokenInfo = JSONObject.parseObject(decrypt, UserTokenInfo.class);
                    Date cur = new Date();
                    if (cur.getTime() > userTokenInfo.getExpirationTime().getTime()) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                }
            }

            ReqThreadInfoUtil.setToken(token);
            // 要继续处理请求，必须添加 filterChain.doFilter()
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw e;
        } finally {
            ReqThreadInfoUtil.removeToken();
        }
    }

    public Boolean isAuthWhiteList(String uri) {
        if (excludedAccuratePaths.contains(uri)) {
            return true;
        }

        for (Pattern pattern : excludedVaguePaths) {
            if (pattern.matcher(uri).matches()) {
                return true;
            }
        }

        return false;
    }
}
