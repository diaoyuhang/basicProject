package com.example.basicproject.controller.user;

import com.example.basicproject.constant.Status;
import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dto.PageReqCondition;
import com.example.basicproject.dto.Pagination;
import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.dto.user.UserReqDto;
import com.example.basicproject.dto.user.UserResDto;
import com.example.basicproject.dto.user.UserRoleReqInfo;
import com.example.basicproject.dto.validGroup.Select;
import com.example.basicproject.dto.validGroup.Update;
import com.example.basicproject.service.PermissionService;
import com.example.basicproject.service.UserService;
import com.example.basicproject.utils.ReqThreadInfoUtil;
import com.example.basicproject.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Long ASSIGN_ROLE_ID = 100104L;
    private static final Long USER_MENU_ID = 1002L;
    private static final Long ADD_USER_ID = 1002001L;
    private static final Long EDIT_USER_ID = 1002002L;

    private UserService userService;
    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

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
        if (!permissionService.validate(USER_MENU_ID)) {
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        Pagination<List<UserResDto>> res = userService.getUserList(pageReqCondition);
        return ResultDto.createSuccess(res);
    }

    @PostMapping("/register")
    public ResultDto<UserResDto> register(@RequestBody UserReqDto userReqDto) {

        UserResDto res = userService.register(userReqDto);
        return ResultDto.createSuccess(res);
    }

    @PostMapping("/addUser")
    public ResultDto<Boolean> addUser(@RequestBody UserReqDto userReqDto) {
        if (!permissionService.validate(ADD_USER_ID)) {
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        userService.register(userReqDto);
        return ResultDto.createSuccess(true);
    }

    @PostMapping("/modifyUser")
    public ResultDto<Boolean> modifyUser(@RequestBody @Validated(value = {Update.class}) UserReqDto userReqDto) {
        if (!permissionService.validate(EDIT_USER_ID)) {
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        userService.modifyUser(userReqDto);
        return ResultDto.createSuccess(true);
    }

    @PostMapping("/assignRole")
    public ResultDto<Boolean> assignRole(@RequestBody @Validated UserRoleReqInfo userRoleReqInfo) {
        if (!permissionService.validate(ASSIGN_ROLE_ID)) {
            return ResultDto.createFail(Status.PERMISSION_ERROR);
        }
        userService.assignRole(userRoleReqInfo);
        return ResultDto.createSuccess(true);
    }

    @GetMapping("/getExistPermission")
    public ResultDto<List<String>> getExistPermission(){
        List<String> pIds = userService.getExistPermission();
        return ResultDto.createSuccess(pIds);
    }

    @PostMapping("/batchStop")
    public ResultDto<Boolean> batchStop(@RequestBody List<String> ids){
        System.out.println(ids);
        userService.batchStop(ids);
        return ResultDto.createSuccess(true);
    }
    @GetMapping("/getUserInfo")
    public ResultDto<UserResDto> getUserInfo(){
        User user = ReqThreadInfoUtil.getUser();
        return ResultDto.createSuccess(UserResDto.create(user));
    }
}
