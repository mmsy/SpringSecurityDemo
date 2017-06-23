package com.nfky.datacenter.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加解密工具类
 * <p>
 * Created by lyr on 2017/6/8.
 */
public class AESCipherUtil {

    /**
     * 加密
     *
     * @param content 待加密内容
     * @param key     秘钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(key.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] bytes = cipher.doFinal(content.getBytes("utf-8"));
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param key     秘钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String key) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(key.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
        return new String(bytes, "utf-8");
    }
}
