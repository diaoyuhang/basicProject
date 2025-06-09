package com.example.basicproject.dao;


import com.example.basicproject.domain.MassageClass;
import com.example.basicproject.dto.massage.MassageClassListReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MassageClassDao {

    int insert(MassageClass record);

    int insertSelective(MassageClass record);

    MassageClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MassageClass record);

    int updateByPrimaryKey(MassageClass record);

    List<MassageClass> selectByMassageClass(MassageClassListReqDto condition);

    MassageClass selectByTitle(String title);
}