package com.example.basicproject.service;

import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.user.UserReqDto;
import com.example.basicproject.dto.user.UserResDto;
import com.example.basicproject.dto.user.UserRoleReqInfo;

import java.util.List;

public interface UserService {
    Pagination<List<UserResDto>> getUserList(PageReqCondition<UserReqDto> pageReqCondition);

    UserResDto register(UserReqDto userReqDto);

    String login(UserReqDto userReqDto);

    void assignRole(UserRoleReqInfo userRoleReqInfo);

    List<String> getExistPermission();

    void modifyUser(UserReqDto userReqDto);

    void batchStop(List<String> ids);
}
