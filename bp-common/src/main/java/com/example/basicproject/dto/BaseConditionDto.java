package com.example.basicproject.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BaseConditionDto {

    private List<Date> createTimeRange;
    private List<Date> editTimeRange;

    private String sortField;

    private String sortOrder;
}
