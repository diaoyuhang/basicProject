package com.example.basicproject.dao;

import com.example.basicproject.dao.domain.Video;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoDao {
    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKeyWithBLOBs(Video record);

    int updateByPrimaryKey(Video record);
}