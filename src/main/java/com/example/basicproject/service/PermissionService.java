package com.example.basicproject.service;

import com.example.basicproject.dto.role.PermissionResDto;

import java.util.List;

public interface PermissionService {

    public Boolean validate(Long permissionId);

    List<PermissionResDto> allPermissionList();

}
