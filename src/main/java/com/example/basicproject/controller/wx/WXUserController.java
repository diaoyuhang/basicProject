package com.example.basicproject.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.dto.user.WxUserTokenInfo;
import com.example.basicproject.http.dto.WXUserResDto;
import com.example.basicproject.service.UserService;
import com.example.basicproject.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/user")
public class WXUserController{
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/wxLogin")
    public ResultDto<String> wxLogin(String jsCode){
        WxUserTokenInfo wxUserTokenInfo = userService.wxLogin(jsCode);
        return ResultDto.createSuccess(SecretUtil.encrypt(JSONObject.toJSONString(wxUserTokenInfo)));
    }
}
