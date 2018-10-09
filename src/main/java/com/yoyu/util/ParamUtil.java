package com.yoyu.util;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by weizw on 2018/10/8.
 */

public class ParamUtil {

    private static String TAG = ParamUtil.class.getSimpleName();

    private static String getSignature(String content, String secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(secret));
            byte[] result = cipher.doFinal(byteContent);
            String base64Result = Base64.getEncoder().encodeToString(result);
            return base64Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SecretKey generateKey(String secret) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();
        random.setSeed(secret.getBytes());
        keyGenerator.init(random);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }


    public static Map<String, String> generateParams(Map<String, String> paramMap) {
        String[] keys = paramMap.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            String value = paramMap.get(key);
            stringBuilder.append(key).append(value);
        }
        paramMap.put("signatrue", getSignature(stringBuilder.toString(), getKey()));
        return paramMap;
    }

    private static String getKey() {
        return "helloworld";
    }

}

