package com.yoyu.util;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateReqUrl {

	private static final String ALGORITHM = "AES";	
	
	/**
	 * 请求格式：http://ip:port/script/check?client_id=xxx&script_id=xxx&timestamp=xxx&signatrue=xxx
	 * 1、请求api地址：例：检查脚本是否过期：http://ip:port/script/check
	 * 2、参数：@param client_id 客户号（购买者id）
	 * 		 @param script_id 脚本编号
	 * 		 @param timestamp 时间戳
	 * 		 @param signatrue 签名
	 * 3、签名signature作用：如果参数被篡改，服务端接受到请求，获取请求参数，用同样算法、密钥生成签名
	 *                   与请求中的signature比对，若不一致，被篡改，拦截请求，否则通过。
	 * 4、时间戳作用：若没有时间戳，请求被非法劫持，劫持者可通过循环无限制调用接口，
	 *           加上时间戳后可以控制时间戳与服务器接受到请求的时间间隔，若间隔超过规定时间（假设5分钟）则判断其为非法请求。
	 * 
	 */
	public static void main(String[] args) {
		
		
		String api = "http://baidu.com/script/check";
		String secret = "helloworld";
		Map<String, String> paramMap = new HashMap<>();
		
		//从脚本中获取client_id、script_id
		paramMap.put("client_id", "8888");
		paramMap.put("script_id", "6666");
		//paramMap.put("timestamp", ( System.currentTimeMillis() / 1000 ) + "");
		paramMap.put("timestamp", "1539008344");
		//请求示例
		System.out.println("请求地址：" +generateReqUrl(api, paramMap,secret) );
		
		//解密示例
	    String encryptStr = "922D671CA3582BAEC09501F521E3DDC8A12135724CA4E954226E3FAC423191A2D174E4F16F7E4B97BABE4A778B";
		System.out.println(encryptStr.length());
		//String encryptStr = "TLHCzCqlteSoyDs40X6erHYRpXyGFcja3YPrlxgVyyD+gyUB8E7lPcJJk+qdWRU3";
		System.out.println("解密：" + decrypt(encryptStr,secret) );
		
	}

	/**
	 * 生成请求地址（带参）
	 * @param api api地址
	 * @param paramMap 参数map
	 * @param secret 加密密钥
	 * @return
	 */
	public static String generateReqUrl(String api, Map<String,String> paramMap, String secret) {
		
		// 1、生成签名signature
		// 1.1、将参数按ASCII码排序
		String[] keys = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		// 1.2、把所有参数名和参数值串在一起
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			String value = paramMap.get(key);
			sb.append(key).append(value);
		}
		System.out.println(sb.toString());
		// 1.3生成signature
		String signatrue = getSignature(sb.toString(), secret);
		
		//2、生成请求地址
		String url = api + "?";
		for (String key : keys) {
			url += key + "=" + paramMap.get(key) + "&";
		}
		url = url + "signatrue=" + signatrue ;
		return url;
	}
	
	/**
	 * 使用AES加密算法生成 signature
	 * @param content
	 * @param secret
	 * @return
	 */
	private static String getSignature(String content, String secret) {
		
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, generateKey(secret)); 
	        byte[] result = cipher.doFinal(byteContent); 
	        String base64Result = Base64.getEncoder().encodeToString(result);
			System.out.println("signature = "+base64Result);
			return  base64Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 解密
	 * @param encryptContent
	 * @param secret
	 * @return
	 */
	private static String decrypt(String encryptContent, String secret) {

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(secret));
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(encryptContent.getBytes("utf-8")));
            return new String(result, "utf-8");
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        
        return null;
    }
	
	/**
	 * 生成密钥
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	private static SecretKey generateKey(String secret) throws Exception {  
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);  
        SecureRandom random = new SecureRandom();  
        random.setSeed(secret.getBytes()); 
        keyGenerator.init(random);  
        SecretKey secretKey = keyGenerator.generateKey();  
        return secretKey;  
    }  
	
}
