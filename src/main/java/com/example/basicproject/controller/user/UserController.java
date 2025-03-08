package com.example.basicproject.controller.user;

import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.dto.user.UserReqDto;
import com.example.basicproject.dto.user.UserResDto;
import com.example.basicproject.dto.validGroup.Select;
import com.example.basicproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultDto<String> login(@RequestBody @Validated(value = {Select.class}) UserReqDto userReqDto) {

        return ResultDto.createSuccess(userService.login(userReqDto));
    }

    @PostMapping("/getUserList")
    public ResultDto<Pagination<List<UserResDto>>> getUserList(@RequestBody PageReqCondition<UserReqDto> pageReqCondition) {
        Pagination<List<UserResDto>> res = userService.getUserList(pageReqCondition);
        return ResultDto.createSuccess(res);
    }

    @PostMapping("/register")
    public ResultDto<UserResDto> register(@RequestBody UserReqDto userReqDto) {
        UserResDto res = userService.register(userReqDto);
        return ResultDto.createSuccess(res);
    }
}
