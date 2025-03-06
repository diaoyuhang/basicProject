package com.example.basicproject.dto;


import com.example.basicproject.constant.Status;

public class ResultDto<T> {

    private String msg;
    private Integer code;

    private T data;

    private ResultDto(){}

    public static <T> ResultDto<T> createSuccess(T date){
        ResultDto<T> resultDto = new ResultDto<>();
        resultDto.code = Status.ok.getStatueCode();
        resultDto.data =date;
        return resultDto;
    }

    public static ResultDto<String> createFail(Status s) {
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.code = s.getStatueCode();
        resultDto.msg = s.getMessage();
        return resultDto;
    }

    public static ResultDto<String> createFail(Status s,String errorMessage) {
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.code = s.getStatueCode();
        resultDto.msg = errorMessage;
        return resultDto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
