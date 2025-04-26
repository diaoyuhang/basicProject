package com.example.basicproject.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RequestResponseLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 1）定义切点：拦截 controller 包下所有公有方法
    @Pointcut("execution(public * com.example.basicproject.controller..*(..))")
    public void controllerMethods() {}

    // 2）环绕通知：前打印请求参数，后打印返回结果及耗时
    @Around("controllerMethods()")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        // 2.1）获取当前请求信息
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            // 非 HTTP 请求环境，直接放行
            return joinPoint.proceed();
        }
        HttpServletRequest request = attrs.getRequest();

        // 2.2）序列化请求参数
        Map<String, String[]> paramMap = request.getParameterMap();
        String paramsJson = objectMapper.writeValueAsString(paramMap);

        String uri = request.getRequestURI();
        String method = request.getMethod();

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> names = request.getHeaderNames();
        while (names != null && names.hasMoreElements()) {
            String name = names.nextElement();
            headers.put(name, request.getHeader(name));
        }
        String headersJson = objectMapper.writeValueAsString(headers);

        log.info("【请求开始】{} {}，请求头: {}，参数: {}", method, uri,headersJson, paramsJson);

        // 2.3）执行目标方法并计时
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsed = System.currentTimeMillis() - start;

        // 2.4）序列化响应结果
        String resultJson;
        try {
            resultJson = objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            resultJson = "<<< 序列化返回值失败: " + e.getMessage() + " >>>";
        }

        log.info("【请求结束】{} {}，耗时: {} ms，返回: {}", method, uri, elapsed, resultJson);
        return result;
    }
}
