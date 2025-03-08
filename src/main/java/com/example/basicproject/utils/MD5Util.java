package com.example.basicproject.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String input) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
            return getImageHash(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getImageHash(InputStream stream){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = stream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            stream.close();

            // 转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest.digest()) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String imagePath = "C:\\Users\\95190\\Downloads\\msedge_g9LDmVmSWd.png"; // 替换为你的图片路径
        String imagePath2 = "C:\\Users\\95190\\Downloads\\msedge_g9LDmVmSWd - 副本.png"; // 替换为你的图片路径
        File file = new File(imagePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println("MD5: " + getImageHash(fileInputStream));
        File file2 = new File(imagePath2);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        System.out.println("MD5: " + getImageHash(fileInputStream2));
    }
}
