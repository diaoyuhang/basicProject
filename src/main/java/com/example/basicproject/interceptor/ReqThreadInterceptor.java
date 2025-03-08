package com.example.basicproject.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dao.UserDao;
import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dto.user.UserTokenInfo;
import com.example.basicproject.utils.ReqThreadInfoUtil;
import com.example.basicproject.utils.SecretUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ReqThreadInterceptor implements HandlerInterceptor {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = ReqThreadInfoUtil.getToken();
        if (!StringUtils.isEmpty(token)){
            UserTokenInfo userTokenInfo = JSONObject.parseObject(SecretUtil.decrypt(token), UserTokenInfo.class);
            User user = userDao.selectByPrimaryKey(userTokenInfo.getId());
            ReqThreadInfoUtil.setUser(user);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ReqThreadInfoUtil.removeUser();
    }
}
