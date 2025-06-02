package com.example.basicproject.controller.front.user;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.dto.user.WxUserEncryptedData;
import com.example.basicproject.dto.user.WxUserInfoResDto;
import com.example.basicproject.dto.user.CoreUserReqDto;
import com.example.basicproject.dto.user.CoreUserTokenInfo;
import com.example.basicproject.service.front.CoreUserService;
import com.example.basicproject.utils.AES128CBCUtils;
import com.example.basicproject.utils.ReqThreadInfoUtil;
import com.example.basicproject.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/fr/user")
public class CoreUserController {
    private CoreUserService coreUserService;

    @Autowired
    public void setCoreUserService(CoreUserService coreUserService) {
        this.coreUserService = coreUserService;
    }

    @GetMapping("/wxLogin")
    public ResultDto<String> wxLogin(String jsCode) {
        CoreUserTokenInfo coreUserTokenInfo = coreUserService.wxLogin(jsCode);
        return ResultDto.createSuccess(SecretUtil.encrypt(JSONObject.toJSONString(coreUserTokenInfo)));
    }

    @GetMapping("/getUserInfo")
    public ResultDto<WxUserInfoResDto> getUserInfo() {
        WxUserInfoResDto wxUserInfoResDto = coreUserService.getUserInfo();
        return ResultDto.createSuccess(wxUserInfoResDto);
    }

    @PostMapping("/decodeUserInfo")
    public ResultDto<String> decodeUserInfo(@RequestBody WxUserEncryptedData wxUserEncryptedData) throws Exception {

        String decrypt = SecretUtil.decrypt(ReqThreadInfoUtil.getToken());
        CoreUserTokenInfo coreUserTokenInfo = JSONObject.parseObject(decrypt, CoreUserTokenInfo.class);
        byte[] encryptedBytes = Base64.getDecoder().decode(wxUserEncryptedData.getEncryptedData());
        byte[] ivBytes = Base64.getDecoder().decode(wxUserEncryptedData.getIv());
        byte[] keyBytes = Base64.getDecoder().decode(coreUserTokenInfo.getSessionKey());
        return ResultDto.createSuccess(AES128CBCUtils.decrypt(encryptedBytes, keyBytes, ivBytes));
    }

    @PostMapping("/saveUserInfo")
    public ResultDto<Boolean> saveUserInfo(@RequestBody CoreUserReqDto coreUserReqDto) {
        coreUserService.saveUserInfo(coreUserReqDto);
        return ResultDto.createSuccess(true);
    }
}
