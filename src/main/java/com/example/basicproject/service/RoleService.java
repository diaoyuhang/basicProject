package com.example.basicproject.service;

import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.role.RoleReqDto;
import com.example.basicproject.dto.role.RoleResDto;

import java.util.List;

public interface RoleService {
    Pagination<List<RoleResDto>> getRoleList(PageReqCondition<RoleReqDto> pageReqCondition);

    void addRole(RoleReqDto roleReqDto);

    void editRole(RoleReqDto roleReqDto);

    void deleteRole(RoleReqDto roleReqDto);
}
