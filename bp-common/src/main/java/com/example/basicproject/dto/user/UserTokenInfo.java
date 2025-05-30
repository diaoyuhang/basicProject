package com.example.basicproject.dto.user;

import java.util.Date;

public class UserTokenInfo {
    private Long id;

    private Date expirationTime;

    public UserTokenInfo() {
    }

    public UserTokenInfo(Long id, Date expirationTime) {
        this.id = id;
        this.expirationTime = expirationTime;
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
}
