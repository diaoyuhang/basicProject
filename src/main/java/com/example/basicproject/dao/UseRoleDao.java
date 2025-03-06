package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.UseRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UseRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(UseRole record);

    int insertSelective(UseRole record);

    UseRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseRole record);

    int updateByPrimaryKey(UseRole record);
}