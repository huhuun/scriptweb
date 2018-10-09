package com.yoyu.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoyu.constant.CommonConstants;


/**
 * 功能说明: 处理请求工具类
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: zhusj20969<br>
 * 开发时间: 2018-08-09<br>
 */
public class RequestUtil {

	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
	
	/**
	 * 获取请求中的业务参数
	 * @param request
	 * @return Map<String, String>
	 */
	public static Map<String, String> getRequestParams(HttpServletRequest request){
		
		//1、获取参数
		//因为request.getParameterMap();获得的参数是一个不可变Map，因此拷贝一份到reqParams
		Map<String, String []> reqMap = request.getParameterMap();
		Map<String, String> reqParams = new HashMap<>();	
		for(Map.Entry<String, String []> entry : reqMap.entrySet()) {
			reqParams.put(entry.getKey(), entry.getValue()[0]);
		}
		
		//2、移除公共参数（）
		reqParams.remove(CommonConstants.COMMON_PARAM_TIMESTAMP);
		reqParams.remove(CommonConstants.COMMON_PARAM_CLIENT_ID);
		reqParams.remove(CommonConstants.COMMON_PARAM_SCRIPT_ID);
		reqParams.remove(CommonConstants.COMMON_PARAM_SIGNATURE);
		//3、判断剩下的业务参数是否为空
		if(reqParams.isEmpty()) {
			logger.info("api参数为空！");
			//throw new ApiException(ApiErrNoConstants.ERR_API_PARAM_EMPTY);
		}
		//4、检验请求中存在的参数的合法性（是否为空）
		// 注：此处只能校验已上传的参数是否为空，为保证方法通用性，接口有哪些业务参数此处不做判断，接口调用另做处理
		//if(!request.getContentType().contains("multipart/form-data")) {}
		//System.err.println("contentType====>" + request.getContentType());
		for(Map.Entry<String, String> entry : reqParams.entrySet()){
			if(StringUtils.isBlank(entry.getValue())) {
				logger.info("api参数[{}]值为空！",entry.getKey());
				//throw new ApiException(ApiErrNoConstants.ERR_API_PARAM_EMPTY);
			}
		}
		
		return reqParams;
	}
	
	/**
	 * 获取请求中所有参数（公共参数 + 业务参数）
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllReqParams(HttpServletRequest request){
		//1、获取参数
		//因为request.getParameterMap();获得的参数是一个不可变Map，因此拷贝一份到reqParams
		Map<String, String []> reqMap = request.getParameterMap();
		Map<String, String> reqParams = new HashMap<>();	
		for(Map.Entry<String, String []> entry : reqMap.entrySet()) {
			reqParams.put(entry.getKey(), entry.getValue()[0]);
		}
		//4、检验请求参数的合法性（是否为空）
		//if(!request.getContentType().contains("multipart/form-data")) {}
		System.err.println("contentType====>" + request.getContentType());
		for(Map.Entry<String, String> entry : reqParams.entrySet()){
			if(StringUtils.isBlank(entry.getValue())) {
				logger.info("api参数[{}]值为空！",entry.getKey());
				//throw new ApiException(ApiErrNoConstants.ERR_API_PARAM_EMPTY);
			}
		}
		
		return reqParams;
		
	}
	
	/**
	 * 生成签名
	 * @param request
	 * @param secret
	 * @return
	 * @throws IOException
	 */
	public static String signRequest(HttpServletRequest request, String secret) throws IOException {
		// 1、检查参数是否已经排序
		Map<String, String> params = getAllReqParams(request);
		params.remove("signature");
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 2、把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
				query.append(key).append(value);
			}
		}

		// 3、使用MD5/HMAC加密
		byte[] bytes = encryptHMAC(query.toString(), secret);

		// 4、把二进制转化为大写的十六进制(正确签名应该为32大写字符串，此方法需要时使用)
		return byte2hex(bytes);
	}
	
	public static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse.toString());
		}
		return bytes;
	}

	public static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}
	
	
	/**判断api参数是否为空
	 * @param param
	 * @return
	 */
	public static String isNotBlank(String param) {
		if(StringUtils.isBlank(param)) {
			logger.info("api参数[{}]值为空！",param);
			//throw new ApiException(ApiErrNoConstants.ERR_API_PARAM_EMPTY);
		}
		return param;
	}
	
	/**
	 * 判断api参数值是否存在空值
	 * @param params
	 * @return
	 */
	public static Boolean isNotBlank(Map<String, String> params) {
		
		Boolean flag = true;
		for(Map.Entry<String, String> entry : params.entrySet()){
			if(StringUtils.isBlank(entry.getValue().toString())) {
				flag = false;
//				logger.info("api参数[{}]值为空！",entry.getKey());
//				throw new ApiException(ApiErrNoConstants.ERR_API_PARAM_EMPTY);
			}
		}
		return flag;
	}
	
	
	/**
	 * 参数转换成对象
	 * @param c
	 * @param params
	 * @return
	 */
//	public static <T> T params2Object(Class<T> c,Map<String,Object> params) {
//		String j = JSONUtils.toJSONString(params);
//		JSONObject json = JSONObject.parseObject(j);
//		//System.out.println(json.toJSONString());
//		T t =(T) JSONObject.toJavaObject(json,c);
//		return t;
//	}
	
	
	/**
	 * 检查字典参数是否在字典值内
	 * @param param 参数
	 * @param range 
	 */
	public static void paramInDict(String param, String [] range) {
		
		Boolean flag = false;
		for (int i = 0; i < range.length; i++) {
			if(param.equals(range[i])) {
				flag = true;
				break;
			}
		}
		if(!flag) {
			logger.info("api参数中类型值非法！",param);
			//throw new ApiException(ApiErrNoConstants.ERR_ILLEGAL_API_PARAM);
		}
	}
	
	/**
	 * 判断对象属性是否全为空，是，返回 true 否：false
	 * @param obj
	 * @return
	 */
	public static Boolean isObjParamsEmpty(Object obj) {
		boolean allEmpty = true;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			if(fields[i].getName().equals("serialVersionUID")) {
				continue;
			}
			Object value = null;
			try {
				value = fields[i].get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			//System.out.println("field="+fields[i].getName()+"-->value="+value);
			if(value != null) {
				allEmpty = false;
				break;
			}
			
		}
		return allEmpty;
	}
	
	
	
	public static void main(String[] args) {
		
	}
	
	
}
