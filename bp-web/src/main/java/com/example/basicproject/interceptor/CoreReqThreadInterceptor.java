package com.example.basicproject.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dao.CoreUserDao;
import com.example.basicproject.domain.User;
import com.example.basicproject.domain.CoreUser;
import com.example.basicproject.dto.user.CoreUserInfoDto;
import com.example.basicproject.dto.user.UserTokenInfo;
import com.example.basicproject.dto.user.CoreUserTokenInfo;
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
public class CoreReqThreadInterceptor implements HandlerInterceptor {
    private CoreUserDao coreUserDao;

    @Autowired
    public void setWxUserDao(CoreUserDao coreUserDao) {
        this.coreUserDao = coreUserDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = ReqThreadInfoUtil.getToken();
        if (!StringUtils.isEmpty(token)){
            CoreUserTokenInfo coreUserTokenInfo = JSONObject.parseObject(SecretUtil.decrypt(token), CoreUserTokenInfo.class);
            CoreUser coreUser = coreUserDao.selectByPrimaryKey(coreUserTokenInfo.getId());
            if (!coreUser.getStatus().equals(User.OPEN_STATUS)){
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return false;
            }

            User user = CoreUserInfoDto.create(coreUser);
            ReqThreadInfoUtil.setUser(user);

            long restTime = 15 * 60 * 1000L;
            //如果token的过期时间就剩下不到15分钟的时间，需要给token续半小时
            if (coreUserTokenInfo.getExpirationTime().getTime() - new Date().getTime() < restTime) {
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
