package com.example.basicproject.dto.user;

import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dao.domain.CoreUser;
import org.springframework.beans.BeanUtils;

public class CoreUserInfoDto extends User {

    private String openId;
    private String unionId;

    private String nickname;

    private String phoneNumber;

    public static User create(CoreUser coreUser) {
        CoreUserInfoDto coreUserInfoDto = new CoreUserInfoDto();
        BeanUtils.copyProperties(coreUser, coreUserInfoDto);

        return coreUserInfoDto;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
