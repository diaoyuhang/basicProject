package com.example.basicproject.service.impl;

import com.example.basicproject.constant.BaseConstant;
import com.example.basicproject.dao.PermissionDao;
import com.example.basicproject.dao.RoleDao;
import com.example.basicproject.dao.domain.Permission;
import com.example.basicproject.dto.role.PermissionResDto;
import com.example.basicproject.service.PermissionService;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.UserHelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private PermissionDao permissionDao;
    private RoleDao roleDao;


    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public Boolean validate(Long permissionId) {
        Long userId = UserHelperUtil.getUser().getId();
        if (userId.equals(BaseConstant.ROOT_USER_ID)) {
            return true;
        }
        Integer num = permissionDao.selectCountByPermissionIdAndUserId(permissionId, userId);
        return num > 0;
    }

    @Override
    public List<PermissionResDto> allPermissionList() {
        List<Permission> allPermission = permissionDao.selectAll();
        return PermissionResDto.buildTree(allPermission);
    }

}
