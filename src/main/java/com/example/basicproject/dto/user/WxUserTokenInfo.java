package com.example.basicproject.dto.user;

import com.example.basicproject.http.dto.WXUserResDto;

import java.util.Date;

public class WxUserTokenInfo {

    private String sessionKey;
    private String openId;

    private Date expirationTime;

    public static WxUserTokenInfo create(WXUserResDto wxUserResDto) {
        WxUserTokenInfo wxUserTokenInfo = new WxUserTokenInfo();
        wxUserTokenInfo.setSessionKey(wxUserResDto.getSessionKey());
        wxUserTokenInfo.setOpenId(wxUserResDto.getOpenId());

        Date cur = new Date();
        Long expireTime = cur.getTime() + 7 * 24 * 60 * 60 * 1000L;
        wxUserTokenInfo.setExpirationTime(new Date(expireTime));

        return wxUserTokenInfo;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
