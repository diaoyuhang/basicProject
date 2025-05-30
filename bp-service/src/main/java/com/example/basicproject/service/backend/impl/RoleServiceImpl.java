package com.example.basicproject.service.backend.impl;

import com.example.basicproject.dao.RoleDao;
import com.example.basicproject.dao.RolePermissionDao;
import com.example.basicproject.dao.UserRoleDao;
import com.example.basicproject.domain.Role;
import com.example.basicproject.domain.RolePermission;
import com.example.basicproject.domain.UserRole;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.role.RolePermissionReqDto;
import com.example.basicproject.dto.role.RoleReqDto;
import com.example.basicproject.dto.role.RoleResDto;
import com.example.basicproject.service.backend.RoleService;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.UserHelperUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;
    private RolePermissionDao rolePermissionDao;
    private UserRoleDao userRoleDao;

    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Autowired
    public void setRolePermissionDao(RolePermissionDao rolePermissionDao) {
        this.rolePermissionDao = rolePermissionDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Pagination<List<RoleResDto>> getRoleList(PageReqCondition<RoleReqDto> pageReqCondition) {
        Page<Object> pageInfo = PageHelper.startPage(pageReqCondition.getPageNum(), pageReqCondition.getPageSize());

        RoleReqDto condition = pageReqCondition.getCondition();
        Role role = new Role();
        if (condition!=null){
            role = condition.convertRole();
        }
        List<Role> roles = roleDao.selectByRole(role);
        List<RoleResDto> res = roles.stream().map(RoleResDto::create).collect(Collectors.toList());
        return Pagination.create(pageInfo.getTotal(),pageInfo.getPageSize(),pageInfo.getPageNum(),res);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleReqDto roleReqDto) {
        Role role = roleReqDto.convertRole();
        UserHelperUtil.fillCreateInfo(role);
        UserHelperUtil.fillEditInfo(role);
        roleDao.insert(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editRole(RoleReqDto roleReqDto) {
        Role role = roleReqDto.convertRole();
        UserHelperUtil.fillEditInfo(role);
        roleDao.updateByPrimaryKeySelective(role);

        if(Role.STOP_STATUS.equals(role.getStatus())){
            userRoleDao.deleteByRoleId(role.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(RoleReqDto roleReqDto) {
        long roleId = IdUtil.decode(roleReqDto.getId()).longValue();
        roleDao.deleteByPrimaryKey(roleId);
        rolePermissionDao.deleteByRoleId(roleId);
        userRoleDao.deleteByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignPermission(RolePermissionReqDto rolePermissionReqDto) {
        List<RolePermission> rolePermissionList = rolePermissionReqDto.convertRolePermission();
        Long roleId = IdUtil.decode(rolePermissionReqDto.getRoleId()).longValue();
        if (rolePermissionList.isEmpty()){
            rolePermissionDao.deleteByRoleId(roleId);
            return;
        }

        for (RolePermission rolePermission : rolePermissionList) {
            UserHelperUtil.fillCreateInfo(rolePermission);
            UserHelperUtil.fillEditInfo(rolePermission);
        }

        List<RolePermission> oldRolePermissions = rolePermissionDao.selectByRoleId(roleId);
        if (oldRolePermissions.isEmpty()){
            rolePermissionDao.batchInsert(rolePermissionList);
            return;
        }
        Set<Long> newPermissionIds = rolePermissionList.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
        List<Long> needDeleteIds = oldRolePermissions.stream().filter(r -> !newPermissionIds.contains(r.getPermissionId())).map(RolePermission::getId).collect(Collectors.toList());

        if (!needDeleteIds.isEmpty()){
            rolePermissionDao.deleteByPrimaryKeys(needDeleteIds);
        }

        Set<Long> oldPermissionIds = oldRolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());

        List<RolePermission> newRolePermissions = rolePermissionList.stream().filter(r -> !oldPermissionIds.contains(r.getPermissionId())).collect(Collectors.toList());
        if (!newRolePermissions.isEmpty()){
            rolePermissionDao.batchInsert(newRolePermissions);
        }
    }

    @Override
    public List<String> getExistPermissionByRoleId(String roleId) {
        Long rid = IdUtil.decode(roleId).longValue();
        List<Long> pIds = rolePermissionDao.selectPermissionIdByRoleId(rid);
        return pIds.stream().map(id->IdUtil.encode(BigInteger.valueOf(id))).collect(Collectors.toList());
    }

    @Override
    public List<String> getPermissionIdRoleId(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)){
            return new ArrayList<>();
        }
        return rolePermissionDao.selectPermissionIdByRoleIds(roleIds).stream().map(id ->IdUtil.encode(BigInteger.valueOf(id))).collect(Collectors.toList());
    }

    @Override
    public List<String> getUsersByRole(Long roleId) {
        List<UserRole> userRoles = userRoleDao.selectByRoleId(roleId);
        List<String> userIds = userRoles.stream().map(u->IdUtil.encode(BigInteger.valueOf(u.getUserId()))).collect(Collectors.toList());
        return userIds;
    }
}
