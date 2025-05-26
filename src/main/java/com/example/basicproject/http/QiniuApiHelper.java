package com.example.basicproject.http;

import com.alibaba.fastjson.JSONObject;
import com.example.basicproject.config.QiNiuConfig;
import com.qiniu.http.Response;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

@Component
public class QiniuApiHelper {
    private final static Logger log = LoggerFactory.getLogger(QiniuApiHelper.class);
    private UploadManager uploadManager;
    private Auth auth;

    private QiNiuConfig qiNiuConfig;

    private RestTemplate qnRestTemplate;

    @Autowired
    public void setQnRestTemplate(RestTemplate qnRestTemplate) {
        this.qnRestTemplate = qnRestTemplate;
    }

    @Autowired
    public void setQiNiuConfig(QiNiuConfig qiNiuConfig) {
        this.qiNiuConfig = qiNiuConfig;
    }

    @Autowired
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Autowired
    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }

    public DefaultPutRet uploadFile(InputStream inputStream,String key){
        try {
            String token = auth.uploadToken(qiNiuConfig.getBucketProd(), key, qiNiuConfig.getExpireTime(), null);
            Response response = uploadManager.put(inputStream, key,token , null, null);
            return JSONObject.parseObject(response.bodyString(),DefaultPutRet.class);
        } catch (Exception e) {
            log.error("uploadFile:"+e.getMessage(),e);
            throw new RuntimeException("七牛云上传文件失败");
        }
    }

    public byte[] downloadFile(String hash){
        try {
            DownloadUrl url = new DownloadUrl(qiNiuConfig.getDomain(), false, hash);
            long deadline = System.currentTimeMillis()/1000 + 3600;
            String urlString = url.buildURL(auth, deadline);
            log.info("downLoadUrl:"+urlString);
            ResponseEntity<byte[]> response = qnRestTemplate.getForEntity(urlString, byte[].class);
            return response.getBody();
        } catch (Exception e) {
            log.error("downloadFile:"+e.getMessage(),e);
            throw new RuntimeException("七牛云获取文件失败");
        }
    }
}
