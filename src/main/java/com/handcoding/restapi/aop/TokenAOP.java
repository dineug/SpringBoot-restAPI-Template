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
import com.handcoding.restapi.exception.TokenBadRequestException;

/**
 * token -> UserVO 주입 AOP
 * @author 이승환
 * @version 2018.01.30 v1.0
 */
@Component
@Aspect
public class TokenAOP {
	
	@Autowired
	private CommonComponent common;
	
	private static final Logger logger = LoggerFactory.getLogger(TokenAOP.class);
	
	@Around("execution(* com..*Controller.*(.., @com.handcoding.restapi.annotation.User (*) ,..))")
	public Object around(final ProceedingJoinPoint j) throws Throwable {
		logger.info("===========================================\t@User 시작\t=====================================");
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = req.getHeader("token");
		if(token != null) {
			UserVO userVO = common.getHandlerToken().getUserVO(token);
			if(userVO != null) {
				// 1번째 방식
				Object[] args = Arrays.stream(j.getArgs()).map(data -> { if(data instanceof UserVO) { data = userVO; } return data; }).toArray();
				return j.proceed(args);
				
				// 2번째 방식
/*				Object[] args = j.getArgs();
				Object[] newArgs = new Object[args.length];
				for (int i = 0; i < args.length; i++) {
					if(args[i] instanceof UserVO) {
						newArgs[i] = userVO;
					}else {
						newArgs[i] = args[i];
					}
				}
				return j.proceed(newArgs);*/
			}
		}
		throw new TokenBadRequestException();
	}
	
}
