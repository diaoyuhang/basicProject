package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    void deleteByRoleId(Long roleId);

    List<RolePermission> selectByRoleId(Long roleId);

    void batchInsert(List<RolePermission> rolePermissionList);

    void deleteByPrimaryKeys(List<Long> ids);

    List<Long> selectPermissionIdByRoleId(Long roleId);

    List<Long> selectPermissionIdByRoleIds(List<Long> roleIds);
}