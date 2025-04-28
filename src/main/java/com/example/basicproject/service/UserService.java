package com.example.basicproject.service;

import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.user.*;
import com.example.basicproject.http.dto.WXUserResDto;

import java.util.List;

public interface UserService {
    Pagination<List<UserResDto>> getUserList(PageReqCondition<UserReqDto> pageReqCondition);

    UserResDto register(UserReqDto userReqDto);

    String login(UserReqDto userReqDto);

    void assignRole(UserRoleReqInfo userRoleReqInfo);

    List<String> getExistPermission();

    void modifyUser(UserReqDto userReqDto);

    void batchStop(List<String> ids);

    WxUserTokenInfo wxLogin(String jsCode);

    WxUserInfoResDto getWxUserInfo();

    void saveWxUserInfo(WxUserReqDto wxUserReqDto);
}
