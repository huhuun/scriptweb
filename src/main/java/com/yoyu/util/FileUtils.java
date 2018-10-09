package com.yoyu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

public class FileUtils {

	/**
	 * 字符串混淆器（根据实际应用可修改）
	 * MD5(fileName+timestamp)
	 * @param fileName
	 * @return
	 */
	public static String generatorMixStr(String str) {
		
		if(StringUtils.isNotBlank(str)) {
			Long ts = ( System.currentTimeMillis() / 1000 );
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				byte[] b = md.digest((str + ts).getBytes());
			    StringBuilder sb = new StringBuilder();
		        for (int i = 0; i < b.length; i++) {
		            String a = Integer.toHexString(0XFF & b[i]);
		            if (a.length() < 2) {
		                a = '0' + a;
		            }
		            sb.append(a);
		        }
			    return sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
