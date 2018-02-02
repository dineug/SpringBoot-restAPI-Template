package com.handcoding.restapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.handcoding.restapi.api.oauth2.OAuth2API;
import com.handcoding.restapi.domain.OAuth2CheckTokenVO;

/**
 * apikey Access 인터셉터
 * @author 이승환
 * @version 2018.02.02 v1.1
 */
@Component
public class InterceptorAccess implements HandlerInterceptor {
	
	@Autowired
	private OAuth2API oAuth2API;
	
	private static final Logger logger = LoggerFactory.getLogger(InterceptorAccess.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("========================================\tAccess 인터셉터 시작 \t=============================================");
		String accessToken = request.getHeader("accessToken");
		accessToken = accessToken != null ? accessToken : "";
		boolean check = false;
		OAuth2CheckTokenVO oAuth2CheckTokenVO = oAuth2API.checkAccessToken(accessToken);
		if(oAuth2CheckTokenVO != null) {
			check = true;
		}else {
			response.sendRedirect("/error/accessToken");
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
