package com.example.basicproject.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

public class AES128CBCUtils {
    public static String decrypt(String cipherTextBase64, String key, String iv) throws Exception {
        // 1. 转换 key 和 iv 为 byte 数组
        byte[] keyBytes = key.getBytes("UTF-8");
        byte[] ivBytes = iv.getBytes("UTF-8");

        // 2. Base64 解码密文
        byte[] cipherBytes = Base64.getDecoder().decode(cipherTextBase64);

        // 3. 设置 Cipher 参数
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 4. 解密
        byte[] decryptedBytes = cipher.doFinal(cipherBytes);
        return new String(decryptedBytes, "UTF-8");
    }

    public static String decrypt(byte[] cipherTextBase64, byte[] key, byte[] iv) throws Exception {


        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(cipherTextBase64);
        return new String(decryptedBytes, "UTF-8");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s="QyVy1ovTSAHEuf3dL7H9bulez+Yc91B6ebT2gM+IgoQ8Hi0w26Y09MLEc0G0yvRveiybpRgKuX0n+bTpMO2rG9W//WFEjtEAmui0h16528ADLyvB8I6h+UIbZKjp0KXawq8+aPHEBACW0n05+myPeLAyY+Euy3ea5cY1ZkfZTE8eaWuuNPy/Xn6ISNPVJQpkBx9sZdnJc2KufPe4OwP72mfYVhpvo8xo19K262LrWVeZtJt6IYoIdHS9U5xOe2ZiTaOrWlPEGJgJxSPqMXIx+HQRwA/LtbCy05wlW1TbBA4dPRaYM4hu0xAJgpcNEstAyAyF8RD1zHvPvu6Kue00MxWBOV0G7NKhH0gTuRfEsS5XJ25HAPKZavRNb4sKeBlbAY9ihpuSLXxZcw2oJOu3elC3Mhp4IK562ZCWCWoU9W8y2/mDY76iPGcxsFa0WpKK";

        System.out.println(Arrays.toString(Base64.getDecoder().decode(s)));
        System.out.println(Arrays.toString(s.getBytes("UTF-8")));
    }
}
