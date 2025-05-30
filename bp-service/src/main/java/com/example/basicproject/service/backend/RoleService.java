package com.example.basicproject.service.backend;

import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.role.RolePermissionReqDto;
import com.example.basicproject.dto.role.RoleReqDto;
import com.example.basicproject.dto.role.RoleResDto;
import com.example.basicproject.dto.user.UserResDto;

import java.util.List;

public interface RoleService {
    Pagination<List<RoleResDto>> getRoleList(PageReqCondition<RoleReqDto> pageReqCondition);

    void addRole(RoleReqDto roleReqDto);

    void editRole(RoleReqDto roleReqDto);

    void deleteRole(RoleReqDto roleReqDto);

    void assignPermission(RolePermissionReqDto rolePermissionReqDto);

    List<String> getExistPermissionByRoleId(String roleId);

    List<String> getPermissionIdRoleId(List<Long> roleIds);

    List<String> getUsersByRole(Long roleId);
}
