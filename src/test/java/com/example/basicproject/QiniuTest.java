package com.example.basicproject;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

@SpringBootTest
public class QiniuTest {

    @Test
    public void testUpload() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "gqo0vqUYxEtd_7tkr8AT7UlNoBz4w_kzJvjBy6FT";
        String secretKey = "WQbBD0FmtP7iWzhfqE0xAaKojBqHAbmeocOU5zRR";
        String bucket = "bpdyh";
        String key = "qiniu_test.jpg";

        try {
            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                System.out.println(response.bodyString());
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                ex.printStackTrace();
                if (ex.response != null) {
                    System.err.println(ex.response);

                    try {
                        String body = ex.response.toString();
                        System.err.println(body);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }

    }

    @Test
    public void testDownload() throws QiniuException {
        String accessKey = "gqo0vqUYxEtd_7tkr8AT7UlNoBz4w_kzJvjBy6FT";
        String secretKey = "WQbBD0FmtP7iWzhfqE0xAaKojBqHAbmeocOU5zRR";
        DownloadUrl url = new DownloadUrl("http://bpdyh.clouddn.com", false, "FsJrNheUCIK7mWcq-BWdrymLwUl1");
//        url.setAttname(attname) // 配置 attname
//                .setFop(fop) // 配置 fop
//                .setStyle(style, styleSeparator, styleParam) // 配置 style
// 带有效期
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        long deadline = System.currentTimeMillis()/1000 + expireInSeconds;
        Auth auth = Auth.create(accessKey, secretKey);
        String urlString = url.buildURL(auth, deadline);
        System.out.println(urlString);

//        String finalUrl = auth.privateDownloadUrl(urlString, expireInSeconds);

    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://bpdyh.clouddn.com/qiniu_test.jpg?e=1748233340&token=gqo0vqUYxEtd_7tkr8AT7UlNoBz4w_kzJvjBy6FT:N00I-Z7ybbYHLThnZExOTZ6-1wA=", String.class);
        System.out.println(response.getBody());
    }
}
