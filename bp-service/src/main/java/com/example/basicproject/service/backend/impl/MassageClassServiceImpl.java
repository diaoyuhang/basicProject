package com.example.basicproject.service.backend.impl;

import com.example.basicproject.dao.MassageClassDao;
import com.example.basicproject.domain.MassageClass;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.massage.MassageClassListReqDto;
import com.example.basicproject.dto.massage.MassageClassResDto;
import com.example.basicproject.dto.massage.MassageClassSaveReqDto;
import com.example.basicproject.service.backend.MassageClassService;
import com.example.basicproject.utils.UserHelperUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MassageClassServiceImpl implements MassageClassService {
    private final static Logger log = LoggerFactory.getLogger(MassageClassServiceImpl.class);
    private MassageClassDao massageClassDao;

    @Autowired
    public void setMassageClassDao(MassageClassDao massageClassDao) {
        this.massageClassDao = massageClassDao;
    }

    @Override
    public Pagination<List<MassageClassResDto>> getMessageClassList(PageReqCondition<MassageClassListReqDto> pageReqCondition) {
        Page<Object> page = PageHelper.startPage(pageReqCondition.getPageNum(), pageReqCondition.getPageSize());
        MassageClassListReqDto condition = pageReqCondition.getCondition();
        if (condition == null){
            condition = new MassageClassListReqDto();
        }

        List<MassageClass> massageClasses = massageClassDao.selectByMassageClass(condition);
        List<MassageClassResDto> res = massageClasses.stream().map(MassageClassResDto::create).collect(Collectors.toList());
        return Pagination.create(page.getTotal(), pageReqCondition.getPageSize(), pageReqCondition.getPageNum(), res);
    }

    @Override
    public void save(MassageClassSaveReqDto massageClassSaveReqDto) {
        MassageClass newMassageClass = massageClassSaveReqDto.convertMassageClass();
        if (newMassageClass.getId() != null) {
            MassageClass oldMassageClass = massageClassDao.selectByPrimaryKey(newMassageClass.getId());
            Assert.notNull(oldMassageClass, newMassageClass.getId() + "未查询到对应的记录");
            BeanUtils.copyProperties(newMassageClass,oldMassageClass);
            UserHelperUtil.fillEditInfo(oldMassageClass);
            massageClassDao.updateByPrimaryKey(oldMassageClass);
        } else {
            MassageClass massageClass = massageClassDao.selectByTitle(massageClassSaveReqDto.getTitle());
            Assert.isNull(massageClass,massageClassSaveReqDto.getTitle()+" 已经存在该分类");

            UserHelperUtil.fillCreateInfo(newMassageClass);
            UserHelperUtil.fillEditInfo(newMassageClass);
            massageClassDao.insert(newMassageClass);
        }
    }

    @Override
    public void stopMessageClass(long id) {
        MassageClass massageClass = massageClassDao.selectByPrimaryKey(id);
        Assert.notNull(massageClass, "未查询到对应的记录");

        massageClass.setState(MassageClass.STOP_STATE);
        UserHelperUtil.fillEditInfo(massageClass);

        massageClassDao.updateByPrimaryKey(massageClass);
    }

    @Override
    public void openMessageClass(long id) {
        MassageClass massageClass = massageClassDao.selectByPrimaryKey(id);
        Assert.notNull(massageClass, "未查询到对应的记录");

        massageClass.setState(MassageClass.OPEN_STATE);
        UserHelperUtil.fillEditInfo(massageClass);

        massageClassDao.updateByPrimaryKey(massageClass);
    }
}
