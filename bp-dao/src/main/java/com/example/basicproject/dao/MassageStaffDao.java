package com.example.basicproject.dao;


import com.example.basicproject.domain.MassageStaff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MassageStaffDao {
    int deleteByPrimaryKey(Long userId);

    int insert(MassageStaff record);

    int insertSelective(MassageStaff record);

    MassageStaff selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(MassageStaff record);

    int updateByPrimaryKey(MassageStaff record);
}