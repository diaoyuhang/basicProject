package com.example.basicproject.utils;


import com.example.basicproject.domain.User;

public class ReqThreadInfoUtil {
    private static final ThreadLocal<String> tokenMap = new ThreadLocal<>();
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static String getToken(){
        return tokenMap.get();
    }

    public static void setToken(String token){
        tokenMap.set(token);
    }

    public static void removeToken(){
        tokenMap.remove();
    }

    public static User getUser(){
        return USER_THREAD_LOCAL.get();
    }

    public static void setUser(User user){
        USER_THREAD_LOCAL.set(user);
    }

    public static void removeUser(){
        USER_THREAD_LOCAL.remove();
    }
}
