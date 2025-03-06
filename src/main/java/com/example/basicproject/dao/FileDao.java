package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao {
    int deleteByPrimaryKey(Long id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKeyWithBLOBs(File record);

    int updateByPrimaryKey(File record);
}