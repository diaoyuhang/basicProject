package com.example.basicproject.dao;

import com.example.basicproject.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Integer selectCountByPermissionIdAndUserId(@Param("permissionId") Long permissionId,@Param("userId") Long userId);

    List<Permission> selectAll();
}