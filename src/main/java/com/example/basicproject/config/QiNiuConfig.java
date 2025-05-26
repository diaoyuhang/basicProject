package com.example.basicproject.config;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "qiniu.config")
public class QiNiuConfig {
    private String accessKeyProd;
    private String secretKeyProd;
    private String bucketProd;

    private String domain;
    private Long expireTime=3600L;

    @Bean
    public Auth auth(){
        Auth auth = Auth.create(accessKeyProd, secretKeyProd);
        return auth;
    }

    @Bean
    public UploadManager uploadManager(){
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = com.qiniu.storage.Configuration.ResumableUploadAPIVersion.V2;
        UploadManager uploadManager = new UploadManager(cfg);
        return uploadManager;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccessKeyProd() {
        return accessKeyProd;
    }

    public void setAccessKeyProd(String accessKeyProd) {
        this.accessKeyProd = accessKeyProd;
    }

    public String getSecretKeyProd() {
        return secretKeyProd;
    }

    public void setSecretKeyProd(String secretKeyProd) {
        this.secretKeyProd = secretKeyProd;
    }

    public String getBucketProd() {
        return bucketProd;
    }

    public void setBucketProd(String bucketProd) {
        this.bucketProd = bucketProd;
    }
}
