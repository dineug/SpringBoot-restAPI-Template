package com.handcoding.restapi.aop;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.handcoding.restapi.annotation.Access;
import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.domain.OAuth2CheckTokenVO;
import com.handcoding.restapi.exception.AccessAuthorityException;

/**
 * AccessToken AOP
 * @author 이승환
 * @version 2018.02.03 v1.0
 */
@Component
@Aspect
public class AccessAOP {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessAOP.class);
	
	@Autowired
	private CommonComponent common;
	
	@Around("@annotation(com.handcoding.restapi.annotation.Access)")
	public Object around(final ProceedingJoinPoint j) throws Throwable {
		logger.info("===========================================\tAccess 시작\t=====================================");
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		boolean check = false;
		String accessToken = req.getHeader("accessToken");
		if(accessToken != null) {
			OAuth2CheckTokenVO oAuth2CheckTokenVO = common.getoAuth2API().checkAccessToken(accessToken);
			if(oAuth2CheckTokenVO != null) {
				MethodSignature signature = (MethodSignature) j.getSignature();
				Method method = signature.getMethod();
				Access accessAnnotation = method.getAnnotation(Access.class);
				String scope = accessAnnotation.scope();
				List<String> scopeList = oAuth2CheckTokenVO.getScope();
				for (String temp : scopeList) {
					if(scope.equals(temp)) {
						check = true;
						break;
					}
				}
			}
		}
		if(check) {
			return j.proceed();
		}else {
			throw new AccessAuthorityException();
		}
	}
	
}
