package com.example.basicproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.constant.BaseConstant;
import com.example.basicproject.dao.UserDao;
import com.example.basicproject.dao.UserRoleDao;
import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dao.domain.UserRole;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.user.UserReqDto;
import com.example.basicproject.dto.user.UserResDto;
import com.example.basicproject.dto.user.UserRoleReqInfo;
import com.example.basicproject.dto.user.UserTokenInfo;
import com.example.basicproject.service.RoleService;
import com.example.basicproject.service.UserService;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.SecretUtil;
import com.example.basicproject.utils.UserHelperUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private UserRoleDao userRoleDao;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Pagination<List<UserResDto>> getUserList(PageReqCondition<UserReqDto> pageReqCondition) {
        Page<Object> page = PageHelper.startPage(pageReqCondition.getPageNum(), pageReqCondition.getPageSize());
        UserReqDto condition = pageReqCondition.getCondition();
        if (condition == null) {
            condition = new UserReqDto();
        }
        List<User> userList = userDao.selectByUser(condition);
        List<UserResDto> res = userList.stream().map(UserResDto::create).collect(Collectors.toList());

        return Pagination.create(page.getTotal(), pageReqCondition.getPageSize(), pageReqCondition.getPageNum(), res);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResDto register(UserReqDto userReqDto) {
        User user = userReqDto.convertUser();
        UserHelperUtil.fillCreateInfo(user);
        UserHelperUtil.fillEditInfo(user);
        userDao.insertSelective(user);

        return UserResDto.create(user);
    }

    @Override
    public String login(UserReqDto userReqDto) {
        User user = userReqDto.convertUser();
        User userInfo = userDao.selectByEmployeeIdAndPassword(user);
        Assert.notNull(userInfo,"用户或密码错误");
        Assert.isTrue(userInfo.getStatus().equals(user.OPEN_STATUS),"用户已被停用");

        Date cur = new Date();
        Long expireTime = cur.getTime() + 7 * 24 * 60 * 60 * 1000L;

        UserTokenInfo userTokenInfo = new UserTokenInfo(userInfo.getId(), new Date(expireTime));

        return SecretUtil.encrypt(JSONObject.toJSONString(userTokenInfo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRole(UserRoleReqInfo userRoleReqInfo) {
        List<UserRole> userRoleList = userRoleReqInfo.convertUserRole();
        Long userId = IdUtil.decode(userRoleReqInfo.getUserId()).longValue();
        Assert.isTrue(!userId.equals(BaseConstant.ROOT_USER_ID), "root用户不需要分配权限");

        //如果分配的角色为空，直接删除所有角色
        if (userRoleList.isEmpty()) {
            userRoleDao.deleteByUserId(userId);
            return;
        }

        for (UserRole userRole : userRoleList) {
            UserHelperUtil.fillCreateInfo(userRole);
            UserHelperUtil.fillEditInfo(userRole);
        }
        List<UserRole> oldUserRoles = userRoleDao.selectByUserId(userId);
        if (CollectionUtils.isEmpty(oldUserRoles)) {
            userRoleDao.batchInsert(userRoleList);
            return;
        }
        Set<Long> newRoleIdSet = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        List<Long> needDeleteRoleIds = oldUserRoles.stream().filter(o -> !newRoleIdSet.contains(o.getRoleId())).map(UserRole::getUserId).collect(Collectors.toList());
        if (!needDeleteRoleIds.isEmpty()) {
            userRoleDao.deleteByPrimaryKeys(needDeleteRoleIds);
        }

        Set<Long> oldRoleId = oldUserRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        List<UserRole> newRoles = userRoleList.stream().filter(o -> !oldRoleId.contains(o.getRoleId())).collect(Collectors.toList());
        if (!newRoles.isEmpty()) {
            userRoleDao.batchInsert(newRoles);
        }

    }

    @Override
    public List<String> getExistPermission() {
        Long uerId = UserHelperUtil.getUser().getId();
        List<UserRole> userRoles = userRoleDao.selectByUserId(uerId);
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        return roleService.getPermissionIdRoleId(roleIds);
    }

    @Override
    public void modifyUser(UserReqDto userReqDto) {
        User user = userReqDto.convertUser();
        UserHelperUtil.fillEditInfo(user);
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void batchStop(List<String> ids) {
        List<Long> idList = ids.stream().map(i -> IdUtil.decode(i).longValue()).collect(Collectors.toList());
        userDao.batchStopByIds(idList);
    }

}
