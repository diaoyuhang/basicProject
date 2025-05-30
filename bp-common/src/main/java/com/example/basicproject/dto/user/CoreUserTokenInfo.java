package com.example.basicproject.dto.user;


import com.example.basicproject.api.dto.WXUserResDto;

import java.util.Date;

public class CoreUserTokenInfo {

    private String sessionKey;
    private String openId;

    private Long id;
    private Date expirationTime;

    public static CoreUserTokenInfo create(Long id, WXUserResDto wxUserResDto) {
        CoreUserTokenInfo coreUserTokenInfo = new CoreUserTokenInfo();
        coreUserTokenInfo.setSessionKey(wxUserResDto.getSession_key());
        coreUserTokenInfo.setOpenId(wxUserResDto.getOpenid());
        coreUserTokenInfo.setId(id);

        Date cur = new Date();
        long expireTime = cur.getTime() + 7 * 24 * 60 * 60 * 1000L;
        coreUserTokenInfo.setExpirationTime(new Date(expireTime));

        return coreUserTokenInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
