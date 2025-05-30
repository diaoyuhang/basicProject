package com.example.basicproject.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dao.UserDao;
import com.example.basicproject.domain.User;
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

import java.util.Date;

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
            if (!user.getStatus().equals(User.OPEN_STATUS)){
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return false;
            }
            ReqThreadInfoUtil.setUser(user);

            long restTime = 15 * 60 * 1000L;
            //如果token的过期时间就剩下不到15分钟的时间，需要给token续半小时
            if (userTokenInfo.getExpirationTime().getTime() - new Date().getTime() < restTime) {
                long expireTime = new Date().getTime() + 30 * 60 * 1000L;
                UserTokenInfo newUserTokenInfo = new UserTokenInfo(user.getId(), new Date(expireTime));
                token = SecretUtil.encrypt(JSONObject.toJSONString(newUserTokenInfo));
            }

            response.addHeader("token", token);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ReqThreadInfoUtil.removeUser();
    }
}
