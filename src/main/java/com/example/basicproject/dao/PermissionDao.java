package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}