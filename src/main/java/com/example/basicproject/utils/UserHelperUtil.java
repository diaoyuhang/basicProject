package com.example.basicproject.utils;

import com.alibaba.fastjson2.JSONObject;
import com.example.basicproject.dao.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;

public class UserHelperUtil {
    private final static Logger logger = LoggerFactory.getLogger(UserHelperUtil.class);
    public static User getUser(){
        try {
            String json = SecretUtil.decrypt(ReqThreadInfoUtil.getToken());
            return JSONObject.parseObject(json,User.class);
        } catch (Exception e) {
            logger.error("getUser:"+e.getMessage(),e);
            throw new RuntimeException("获取用户信息失败");
        }
    }

    public static void fillCreateInfo(Object object)  {
        try {
            User user = getUser();
            Method setCreateTime = object.getClass().getDeclaredMethod("setCreateTime",Date.class);
            Method setCreator = object.getClass().getDeclaredMethod("setCreator",String.class);

            setCreateTime.invoke(object,new Date());
            setCreator.invoke(object,user.getEmail());
        } catch (Exception e) {
            logger.error("fillCreateInfo:"+e.getMessage(),e);
            throw new RuntimeException("填充创建信息异常");
        }
    }

    public static void fillEditInfo(Object object)  {
        try {
            User user = getUser();
            Method setEditTime = object.getClass().getDeclaredMethod("setEditTime",Date.class);
            Method setEditor = object.getClass().getDeclaredMethod("setEditor",String.class);

            setEditTime.invoke(object,new Date());
            setEditor.invoke(object,user.getEmail());
        } catch (Exception e) {
            logger.error("fillEditInfo:"+e.getMessage(),e);
            throw new RuntimeException("填充编辑信息异常");
        }
    }
}
