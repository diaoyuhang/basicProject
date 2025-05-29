package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.CoreUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoreUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(CoreUser record);

    int insertSelective(CoreUser record);

    CoreUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoreUser record);

    int updateByPrimaryKey(CoreUser record);

    CoreUser selectByOpenId(String openId);
}