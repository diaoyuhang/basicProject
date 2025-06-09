package com.example.basicproject.dao;


import com.example.basicproject.domain.MassageProjectStaffRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MassageProjectStaffRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(MassageProjectStaffRelation record);

    int insertSelective(MassageProjectStaffRelation record);

    MassageProjectStaffRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MassageProjectStaffRelation record);

    int updateByPrimaryKey(MassageProjectStaffRelation record);
}