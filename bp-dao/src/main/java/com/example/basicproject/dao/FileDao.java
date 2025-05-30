package com.example.basicproject.dao;

import com.example.basicproject.domain.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileDao {
    int deleteByPrimaryKey(Long id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKeyWithBLOBs(File record);

    int updateByPrimaryKey(File record);

    Long selectIdByMD5(@Param("md5") String md5);
}