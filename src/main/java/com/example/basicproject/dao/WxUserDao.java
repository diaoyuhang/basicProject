package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.WxUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(WxUser record);

    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);

    WxUser selectByOpenId(String openId);
}