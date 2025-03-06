package com.example.basicproject.utils;

import java.math.BigInteger;

public class IdEncryptUtil {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final BigInteger BASE = BigInteger.valueOf(ALPHABET.length());
    private static final BigInteger MULTIPLIER = new BigInteger("987654319987654319"); // 质数，避免冲突
    private static final BigInteger XOR_KEY = new BigInteger("1234567890123456789"); // 大数 XOR 混淆

    public static String encode(BigInteger id) {
        BigInteger obfuscated = id.multiply(MULTIPLIER).xor(XOR_KEY); // 乘法 + 异或混淆
        return toBase36(obfuscated);
    }

    public static BigInteger decode(String encoded) {
        BigInteger num = fromBase36(encoded);
        return num.xor(XOR_KEY).divide(MULTIPLIER); // 先 XOR 还原，再除回去
    }

    private static String toBase36(BigInteger num) {
        StringBuilder sb = new StringBuilder();
        boolean negative = num.signum() < 0;
        num = num.abs();

        while (num.compareTo(BigInteger.ZERO) > 0) {
            sb.insert(0, ALPHABET.charAt(num.mod(BASE).intValue()));
            num = num.divide(BASE);
        }

        while (sb.length() < 10) { // 固定长度
            sb.insert(0, '0');
        }

        return negative ? "Z" + sb.toString() : sb.toString(); // 负数标记
    }

    private static BigInteger fromBase36(String str) {
        boolean negative = str.startsWith("Z");
        if (negative) {
            str = str.substring(1);
        }

        BigInteger num = BigInteger.ZERO;
        for (char c : str.toCharArray()) {
            num = num.multiply(BASE).add(BigInteger.valueOf(ALPHABET.indexOf(c)));
        }
        return negative ? num.negate() : num;
    }

    public static void main(String[] args) {
        BigInteger id = new BigInteger("987654321098876543");
        String encoded = encode(id);
        BigInteger decoded = decode(encoded);

        System.out.println("原始 ID: " + id);
        System.out.println("加密 ID: " + encoded);
        System.out.println("解密 ID: " + decoded);
        System.out.println("解密是否正确: " + id.equals(decoded));
    }
}
