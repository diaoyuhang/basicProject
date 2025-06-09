package com.example.basicproject.controller.backend.massage;

import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.dto.massage.MassageClassListReqDto;
import com.example.basicproject.dto.massage.MassageClassResDto;
import com.example.basicproject.dto.massage.MassageClassSaveReqDto;
import com.example.basicproject.dto.validGroup.Delete;
import com.example.basicproject.dto.validGroup.Insert;
import com.example.basicproject.dto.validGroup.Update;
import com.example.basicproject.service.backend.MassageClassService;
import com.example.basicproject.utils.IdUtil;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/massageClass")
public class MassageClassController {

    private MassageClassService massageClassService;

    @Autowired
    public void setMessageClassService(MassageClassService massageClassService) {
        this.massageClassService = massageClassService;
    }

    @PostMapping("/getMessageClassList")
    public ResultDto<Pagination<List<MassageClassResDto>>> getMessageClassList(@RequestBody PageReqCondition<MassageClassListReqDto> pageReqCondition){
        Pagination<List<MassageClassResDto>> res = massageClassService.getMessageClassList(pageReqCondition);
        return ResultDto.createSuccess(res);
    }

    @PostMapping("/addMessageClass")
    public ResultDto<Boolean> addMessageClass(@RequestBody @Validated(value = {Insert.class}) MassageClassSaveReqDto massageClassSaveReqDto){
        massageClassService.save(massageClassSaveReqDto);
        return ResultDto.createSuccess(true);
    }

    @PostMapping("/updateMessageClass")
    public ResultDto<Boolean> updateMessageClass(@RequestBody @Validated(value = {Update.class}) MassageClassSaveReqDto massageClassSaveReqDto){
        massageClassService.save(massageClassSaveReqDto);
        return ResultDto.createSuccess(true);
    }

    @GetMapping("/stopMessageClass")
    public ResultDto<Boolean> stopMessageClass(@NotEmpty(message = "id不能为空") String id){
        massageClassService.stopMessageClass(IdUtil.decode(id).longValue());
        return ResultDto.createSuccess(true);
    }

    @GetMapping("/openMessageClass")
    public ResultDto<Boolean> openMessageClass(@NotEmpty(message = "id不能为空") String id){
        massageClassService.openMessageClass(IdUtil.decode(id).longValue());
        return ResultDto.createSuccess(true);
    }
}
