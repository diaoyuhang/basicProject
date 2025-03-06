package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}