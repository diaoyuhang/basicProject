package com.example.basicproject.http;

import com.example.basicproject.http.dto.WXUserResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WXApiHelper {

    @Value("${wx_appId}")
    private String appId;

    @Value("${wx_appSecret}")
    private String appSecret;
    private RestTemplate wxRestTemplate;

    @Autowired
    public void setWxRestTemplate(RestTemplate wxRestTemplate) {
        this.wxRestTemplate = wxRestTemplate;
    }

    /**
     * 文档地址 https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-login/code2Session.html
     * @param jsCode
     * @return
     */
    public WXUserResDto jsCode2session(String jsCode){
        String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",appId,appSecret,jsCode);
        ResponseEntity<WXUserResDto> response = wxRestTemplate.getForEntity(url, WXUserResDto.class);

        return response.getBody();
    }


}
