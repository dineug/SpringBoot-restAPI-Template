package com.handcoding.restapi.aop;

import java.lang.annotation.Annotation;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.component.ServiceComponent;
import com.handcoding.restapi.domain.ApiClientAuthVO;
import com.handcoding.restapi.domain.ApiVO;
import com.handcoding.restapi.domain.OAuth2CheckTokenVO;
import com.handcoding.restapi.exception.AccessAuthorityException;

/**
 * AccessToken AOP
 * @author 이승환
 * @version 2018.02.04 v1.1
 */
@Component
@Aspect
public class AccessAOP {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessAOP.class);
	
	@Autowired
	private CommonComponent common;
	
	@Autowired
	private ServiceComponent service;
	
	@Around("@annotation(com.handcoding.restapi.annotation.Access)")
	public Object around(final ProceedingJoinPoint j) throws Throwable {
		logger.info("===========================================\tAccess 시작\t=====================================");
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		boolean check = false;
		String accessToken = req.getHeader("accessToken");
		if(accessToken != null) {
			OAuth2CheckTokenVO oAuth2CheckTokenVO = common.getoAuth2API().checkAccessToken(accessToken);
			if(oAuth2CheckTokenVO != null) {
				ApiVO apiVO = getApiInfo(j);
				List<String> scopeList = oAuth2CheckTokenVO.getScope();
				
				ApiClientAuthVO apiClientAuthVO = new ApiClientAuthVO();
				apiClientAuthVO.setClient_id(oAuth2CheckTokenVO.getClient_id());
				List<ApiClientAuthVO> apiClientAuthList = service.getApiService().apiClientAuthList(apiClientAuthVO);
				
				for (ApiClientAuthVO temp : apiClientAuthList) {
					if(temp.getUrl().equals(apiVO.getUrl()) && temp.getMethod().equals(apiVO.getMethod())) {
						apiVO.setScope(temp.getScope());
						break;
					}
				}
				
				for (String temp : scopeList) {
					if(apiVO.getScope().equals(temp)) {
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
	
	/**
	 * 호출된 API 정보
	 * @param j
	 * @return
	 * @throws ClassNotFoundException
	 */
	private ApiVO getApiInfo(ProceedingJoinPoint j) throws ClassNotFoundException {
		ApiVO apiVO = new ApiVO();
		
		String beanClassName = j.getTarget().toString().split("@")[0];
		Class<?> bean = Class.forName(beanClassName);
		String rootUrl = ((RequestMapping) bean.getAnnotation(RequestMapping.class)).value()[0];
		
		MethodSignature signature = (MethodSignature) j.getSignature();
		Method method = signature.getMethod();
		
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if(annotation instanceof GetMapping) {
				apiVO.setMethod("GET");
				apiVO.setUrl( rootUrl + ( ((GetMapping) annotation)).value()[0] );
				break;
			}else if(annotation instanceof PostMapping) {
				apiVO.setMethod("POST");
				apiVO.setUrl( rootUrl + ( ((PostMapping) annotation)).value()[0] );
				break;
			}else if(annotation instanceof PutMapping) {
				apiVO.setMethod("PUT");
				apiVO.setUrl( rootUrl + ( ((PutMapping) annotation)).value()[0] );
				break;
			}else if(annotation instanceof DeleteMapping) {
				apiVO.setMethod("DELETE");
				apiVO.setUrl( rootUrl + ( ((DeleteMapping) annotation)).value()[0] );
				break;
			}
		}
		return apiVO;
	}
	
}
