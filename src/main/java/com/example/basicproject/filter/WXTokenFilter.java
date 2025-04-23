package com.example.basicproject.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dto.user.WxUserTokenInfo;
import com.example.basicproject.utils.ReqThreadInfoUtil;
import com.example.basicproject.utils.SecretUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class WXTokenFilter extends AbsTokenRule {

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
                    WxUserTokenInfo wxUserTokenInfo = JSONObject.parseObject(decrypt, WxUserTokenInfo.class);
                    Date cur = new Date();
                    if (cur.getTime() > wxUserTokenInfo.getExpirationTime().getTime()) {
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
}
