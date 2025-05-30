package com.example.basicproject.service.front;

import com.example.basicproject.dto.user.WxUserInfoResDto;
import com.example.basicproject.dto.user.WxUserReqDto;
import com.example.basicproject.dto.user.CoreUserTokenInfo;

public interface CoreUserService {
    CoreUserTokenInfo wxLogin(String jsCode);

    WxUserInfoResDto getUserInfo();

    void saveUserInfo(WxUserReqDto wxUserReqDto);
}
