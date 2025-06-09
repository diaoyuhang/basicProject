package com.example.basicproject.dao;


import com.example.basicproject.domain.CoreMassageReserve;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoreMassageReserveDao {
    int deleteByPrimaryKey(Long id);

    int insert(CoreMassageReserve record);

    int insertSelective(CoreMassageReserve record);

    CoreMassageReserve selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoreMassageReserve record);

    int updateByPrimaryKey(CoreMassageReserve record);
}