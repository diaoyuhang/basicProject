package com.example.basicproject.service.impl;

import com.example.basicproject.dao.RoleDao;
import com.example.basicproject.dao.domain.Role;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.role.RoleReqDto;
import com.example.basicproject.dto.role.RoleResDto;
import com.example.basicproject.service.RoleService;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.UserHelperUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

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
        List<RoleResDto> res = roleDao.selectByRole(role);
        return Pagination.create(pageInfo.getTotal(),pageInfo.getPageSize(),pageInfo.getPageNum(),res);
    }

    @Override
    public void addRole(RoleReqDto roleReqDto) {
        Role role = roleReqDto.convertRole();
        UserHelperUtil.fillCreateInfo(role);
        UserHelperUtil.fillEditInfo(role);
        roleDao.insert(role);
    }

    @Override
    public void editRole(RoleReqDto roleReqDto) {
        Role role = roleReqDto.convertRole();
        UserHelperUtil.fillEditInfo(role);
        roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRole(RoleReqDto roleReqDto) {
        roleDao.deleteByPrimaryKey(IdUtil.decode(roleReqDto.getId()).longValue());
    }
}
