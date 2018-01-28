package com.handcoding.restapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.handcoding.restapi.component.CommonComponent;

/**
 * apikey Access 인터셉터
 * @author 이승환
 * @version 2018.01.28 v1.0
 */
@Component
public class InterceptorAccess implements HandlerInterceptor {
	
	@Autowired
	private CommonComponent common;
	
	private static final Logger logger = LoggerFactory.getLogger(InterceptorAccess.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("========================================\tAccess 인터셉터 시작 \t=============================================");
		
		String apiKey = common.getConfig().getApiKey();
		String headerApiKey = request.getHeader("apiKey");
		headerApiKey = headerApiKey != null ? headerApiKey : "";
		boolean check = false;
		if(apiKey.equals(headerApiKey)) {
			check = true;
		}else {
			response.sendRedirect("/error/apikey");
		}
		return check;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
