package com.handcoding.restapi.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.domain.UserVO;

@Component
@Aspect
public class TokenAOP {
	
	@Autowired
	private CommonComponent common;
	
	private static final Logger logger = LoggerFactory.getLogger(TokenAOP.class);
	
	@Around("execution(* com..*Controller.*(.., @com.handcoding.restapi.annotation.User (*) ,..))")
	public Object around(final ProceedingJoinPoint j) throws Throwable {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = req.getHeader("token");
		if(token != null) {
			logger.info("===========================================\t@User 시작\t=====================================");
			UserVO userVO = common.getHandlerToken().getUserVO(token);
			Object[] args = Arrays.stream(j.getArgs()).map(data -> { if(data instanceof UserVO) { data = userVO; } return data; }).toArray();
			return j.proceed(args);
		}else {
			return j.proceed();
		}
	}
	
}
