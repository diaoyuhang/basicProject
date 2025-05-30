package com.example.basicproject.service.front.impl;

import com.example.basicproject.dao.CoreUserDao;
import com.example.basicproject.domain.CoreUser;
import com.example.basicproject.dto.user.CoreUserInfoDto;
import com.example.basicproject.dto.user.WxUserInfoResDto;
import com.example.basicproject.dto.user.WxUserReqDto;
import com.example.basicproject.dto.user.CoreUserTokenInfo;
import com.example.basicproject.http.WXApiHelper;
import com.example.basicproject.api.dto.WXUserResDto;
import com.example.basicproject.service.front.CoreUserService;
import com.example.basicproject.utils.IdUtil;
import com.example.basicproject.utils.ReqThreadInfoUtil;
import com.example.basicproject.utils.UserHelperUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreUserServiceImpl implements CoreUserService {
    private final static Logger log = LoggerFactory.getLogger(CoreUserServiceImpl.class);
    private WXApiHelper wxApiHelper;
    private CoreUserDao coreUserDao;

    @Autowired
    public void setWxUserDao(CoreUserDao coreUserDao) {
        this.coreUserDao = coreUserDao;
    }

    @Autowired
    public void setWxApiHelper(WXApiHelper wxApiHelper) {
        this.wxApiHelper = wxApiHelper;
    }

    @Override
    public CoreUserTokenInfo wxLogin(String jsCode) {
        WXUserResDto wxUserResDto = wxApiHelper.jsCode2session(jsCode);
        CoreUser coreUser = coreUserDao.selectByOpenId(wxUserResDto.getOpenid());
        if (coreUser == null) {
            coreUser = new CoreUser();
            coreUser.setOpenId(wxUserResDto.getOpenid());
            coreUser.setNickname("游客");
            UserHelperUtil.fillCreateInfo(coreUser);
            UserHelperUtil.fillEditInfo(coreUser);
            coreUserDao.insertSelective(coreUser);
        }

        return CoreUserTokenInfo.create(coreUser.getId(),wxUserResDto);
    }

    @Override
    public WxUserInfoResDto getUserInfo() {
        CoreUserInfoDto user = (CoreUserInfoDto) ReqThreadInfoUtil.getUser();
        CoreUser coreUser = coreUserDao.selectByOpenId(user.getOpenId());
        return WxUserInfoResDto.create(coreUser);
    }

    @Override
    public void saveUserInfo(WxUserReqDto wxUserReqDto) {
        CoreUserInfoDto user = (CoreUserInfoDto) ReqThreadInfoUtil.getUser();
        CoreUser coreUser = coreUserDao.selectByOpenId(user.getOpenId());
        coreUser.setNickname(wxUserReqDto.getNickname());
        if (!StringUtils.isEmpty(wxUserReqDto.getAvatar())) {
            coreUser.setAvatar(IdUtil.decode(wxUserReqDto.getAvatar()).longValue());
        }
        UserHelperUtil.fillEditInfo(coreUser);
        coreUserDao.updateByPrimaryKey(coreUser);
    }
}
