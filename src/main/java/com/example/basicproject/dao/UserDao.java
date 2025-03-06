package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.user.UserReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByUser(User user);
}