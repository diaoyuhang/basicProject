package com.example.basicproject.service.impl;

import com.example.basicproject.dao.UserDao;
import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.user.UserReqDto;
import com.example.basicproject.dto.user.UserResDto;
import com.example.basicproject.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Pagination<List<UserResDto>> getUserList(PageReqCondition<UserReqDto> pageReqCondition) {
        Page<Object> page = PageHelper.startPage(pageReqCondition.getPageNum(), pageReqCondition.getPageSize());
        UserReqDto condition = pageReqCondition.getCondition();
        if (condition==null){
            condition = new UserReqDto();
        }
        List<User> userList = userDao.selectByUser(condition.convertUser());
//        PageInfo<User> pageInfo = new PageInfo<>(userList);

        List<UserResDto> res = userList.stream().map(UserResDto::create).collect(Collectors.toList());

        return Pagination.create(page.getTotal(), pageReqCondition.getPageSize(),pageReqCondition.getPageNum(),res);
    }

    @Override
    public UserResDto register(UserReqDto userReqDto) {
        User user = userReqDto.convertNewUser();
        userDao.insertSelective(user);
        return UserResDto.create(user);
    }
}
