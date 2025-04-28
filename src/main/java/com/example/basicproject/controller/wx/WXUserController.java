package com.example.basicproject.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dao.domain.User;
import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.dto.user.*;
import com.example.basicproject.http.dto.WXUserResDto;
import com.example.basicproject.service.UserService;
import com.example.basicproject.utils.AES128CBCUtils;
import com.example.basicproject.utils.ReqThreadInfoUtil;
import com.example.basicproject.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

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

    @GetMapping("/getUserInfo")
    public ResultDto<WxUserInfoResDto> getUserInfo(){
        WxUserInfoResDto wxUserInfoResDto = userService.getWxUserInfo();
        return ResultDto.createSuccess(wxUserInfoResDto);
    }

    @PostMapping("/decodeUserInfo")
    public ResultDto<String> decodeUserInfo(@RequestBody WxUserEncryptedData wxUserEncryptedData) throws Exception {

        String decrypt = SecretUtil.decrypt(ReqThreadInfoUtil.getToken());
        WxUserTokenInfo wxUserTokenInfo = JSONObject.parseObject(decrypt, WxUserTokenInfo.class);
        byte[] encryptedBytes = Base64.getDecoder().decode(wxUserEncryptedData.getEncryptedData());
        byte[] ivBytes = Base64.getDecoder().decode(wxUserEncryptedData.getIv());
        byte[] keyBytes = Base64.getDecoder().decode(wxUserTokenInfo.getSessionKey());
        return ResultDto.createSuccess(AES128CBCUtils.decrypt(encryptedBytes,keyBytes,ivBytes));
    }

    @PostMapping("/saveUserInfo")
    public ResultDto<Boolean> saveUserInfo(@RequestBody WxUserReqDto wxUserReqDto){
        userService.saveWxUserInfo(wxUserReqDto);
        return ResultDto.createSuccess(true);
    }
}
