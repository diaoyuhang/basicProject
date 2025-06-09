package com.example.basicproject.dao;


import com.example.basicproject.domain.MassageProject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MassageProjectDao {
    int deleteByPrimaryKey(Long id);

    int insert(MassageProject record);

    int insertSelective(MassageProject record);

    MassageProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MassageProject record);

    int updateByPrimaryKey(MassageProject record);
}