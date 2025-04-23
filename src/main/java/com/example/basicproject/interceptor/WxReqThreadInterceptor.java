package com.example.basicproject.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dao.WxUserDao;
import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dao.domain.WxUser;
import com.example.basicproject.dto.user.WxUserInfoDto;
import com.example.basicproject.dto.user.WxUserTokenInfo;
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
public class WxReqThreadInterceptor  implements HandlerInterceptor {
    private WxUserDao wxUserDao;

    @Autowired
    public void setWxUserDao(WxUserDao wxUserDao) {
        this.wxUserDao = wxUserDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = ReqThreadInfoUtil.getToken();
        if (!StringUtils.isEmpty(token)){
            WxUserTokenInfo wxUserTokenInfo = JSONObject.parseObject(SecretUtil.decrypt(token), WxUserTokenInfo.class);
            WxUser wxUser = wxUserDao.selectByOpenId(wxUserTokenInfo.getOpenId());
            if (!wxUser.getStatus().equals(User.OPEN_STATUS)){
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return false;
            }

            User user = WxUserInfoDto.create(wxUser);
            ReqThreadInfoUtil.setUser(user);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ReqThreadInfoUtil.removeUser();
    }
}
