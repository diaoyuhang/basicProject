package com.example.basicproject.dao;


import com.example.basicproject.dao.domain.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentDao {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}