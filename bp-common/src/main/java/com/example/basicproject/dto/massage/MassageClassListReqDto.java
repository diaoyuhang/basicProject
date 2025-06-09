package com.example.basicproject.dto.massage;

import com.example.basicproject.dto.BaseConditionDto;
import lombok.Data;

@Data
public class MassageClassListReqDto extends BaseConditionDto {


    private String title;
    private Integer state;
}
