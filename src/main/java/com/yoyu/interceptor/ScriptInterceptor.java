package com.yoyu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yoyu.constant.CommonConstants;
import com.yoyu.exception.ScriptException;
import com.yoyu.util.RequestUtil;

public class ScriptInterceptor implements HandlerInterceptor {
	
	private final static Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println("进入自定义拦截器！");
		//1、校验公共参数是否齐全	
		String timestamp = request.getParameter(CommonConstants.COMMON_PARAM_TIMESTAMP);
		String clientId = request.getParameter(CommonConstants.COMMON_PARAM_CLIENT_ID);
		String scriptId = request.getParameter(CommonConstants.COMMON_PARAM_SCRIPT_ID);
		String signature = request.getParameter(CommonConstants.COMMON_PARAM_SIGNATURE);
		logger.info("timestamp---->" + timestamp );
		logger.info("clientId---->" + clientId );
		logger.info("scriptId---->" + scriptId );
		logger.info("signature---->" + signature );
		if (StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(clientId)
				 || StringUtils.isEmpty(signature) ||  StringUtils.isEmpty(scriptId)) {
			throw new ScriptException("请求参数为空！");
			
		}else {
			//2、检查时间戳超时，暂定请求时间与系统时间间隔不得超过10分钟
			int nowSt = (int) ( System.currentTimeMillis() / 1000 );
			int reqSt = Integer.parseInt(timestamp);
			long differ =(nowSt - reqSt)/60;
	
			logger.info("请求间隔时间---->" + differ );
			if(differ > 10) {
				throw new ScriptException("请求时间间隔超过10分钟！");
			}
			
			//3、检查access_token 是否属于client_id
//			List<OAuth2AccessToken> list = ( List<OAuth2AccessToken> )tokenStore.findTokensByClientId(clientId);
//			if(list.size() <= 0) {
//				throw new ApiException(ApiErrNoConstants.ERR_INVALID_ACCESS_TOKEN);
//			}
			if(!"6666".equals(scriptId)) {
				throw new ScriptException("脚本编号有误！");
			}
			
			if(!"8888".equals(clientId)) {
				throw new ScriptException("客户编号有误！");
			}
			
			
			//4、验证签名signature
			//4.1获取clientSecret
			//String clientSecret = getClientSecret(clientId);
			String secret = CommonConstants.SECRET;
			//4.2构造signature
			String _signature = RequestUtil.signRequest(request, secret);
			
			logger.info("signature="+signature);
			logger.info("_signature1="+ _signature);
			//4.3比较
			if(!signature.equals(_signature)) {
				throw new ScriptException("签名不一致！");
			}
		}
		return true;
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
