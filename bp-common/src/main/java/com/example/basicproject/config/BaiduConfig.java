package com.example.basicproject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "baidu.config")
public class BaiduConfig {

    private String natureMapAk;

}
