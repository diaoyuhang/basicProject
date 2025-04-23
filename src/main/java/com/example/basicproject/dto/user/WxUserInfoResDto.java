package com.example.basicproject.dto.user;

import com.example.basicproject.dao.domain.WxUser;
import com.example.basicproject.utils.IdUtil;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.Date;

public class WxUserInfoResDto {
    private String id;
    private String openId;
    private String nickname;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别|0-女，1-男
     */
    private Integer gender;

    /**
     * 头像|file.id
     */
    private String avatar;
    private String phoneNumber;

    /**
     * 状态|0-未激活，1-激活，2-停用
     */
    private Integer status;

    public static WxUserInfoResDto create(WxUser wxUser) {
        WxUserInfoResDto wxUserInfoResDto = new WxUserInfoResDto();
        BeanUtils.copyProperties(wxUser,wxUserInfoResDto,"id");
        wxUserInfoResDto.setId(IdUtil.encode(BigInteger.valueOf(wxUser.getId())));

        return wxUserInfoResDto;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
