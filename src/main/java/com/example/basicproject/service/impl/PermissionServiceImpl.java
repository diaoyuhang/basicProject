package com.example.basicproject.service.impl;

import com.example.basicproject.dao.PermissionDao;
import com.example.basicproject.dao.RoleDao;
import com.example.basicproject.service.PermissionService;
import com.example.basicproject.utils.UserHelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Integer num = permissionDao.selectCountByPermissionIdAndUserId(permissionId, UserHelperUtil.getUser().getId());
        return num > 0;
    }
}
