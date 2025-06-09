package com.example.basicproject.service.backend;

import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.massage.MassageClassListReqDto;
import com.example.basicproject.dto.massage.MassageClassResDto;
import com.example.basicproject.dto.massage.MassageClassSaveReqDto;

import java.util.List;

public interface MassageClassService {
    Pagination<List<MassageClassResDto>> getMessageClassList(PageReqCondition<MassageClassListReqDto> pageReqCondition);

    void save(MassageClassSaveReqDto massageClassSaveReqDto);

    void stopMessageClass(long id);

    void openMessageClass(long id);
}
