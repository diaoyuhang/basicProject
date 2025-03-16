package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Mapper
public interface UserRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    void deleteByUserId(Long userId);

    List<UserRole> selectByUserId(Long userId);

    void batchInsert(List<UserRole> userRoles);

    void deleteByPrimaryKeys(List<Long> ids);

    void deleteByRoleId(Long roleId);

    List<UserRole> selectByRoleId(Long roleId);
}