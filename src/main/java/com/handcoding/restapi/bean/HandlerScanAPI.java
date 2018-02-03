package com.handcoding.restapi.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.handcoding.restapi.annotation.Access;
import com.handcoding.restapi.component.ServiceComponent;
import com.handcoding.restapi.domain.ApiVO;

import io.swagger.annotations.ApiOperation;

/**
 * API 정보 스캔 및 초기화처리
 * @author 이승환
 * @version 2018.02.04 v1.0
 */
@Component
public class HandlerScanAPI {
	
	@Autowired
	private ServiceComponent service;
	
	private ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
	private String basePackage = "com.handcoding.restapi.controller";
	private List<ApiVO> apiList = new ArrayList<>();
	
	{
		scanner.addIncludeFilter(new AnnotationTypeFilter(RequestMapping.class));
	}
	
	/**
	 * 초기화 콜백 등록
	 */
	@PostConstruct
	private void apiInit() {
		this.beans();
		service.getApiService().apiInit(apiList);
	}
	
	/**
	 * bean 검색
	 */
	private void beans() {
		String beanClassName = null;
		Class<?> bean = null;
		for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
			beanClassName = bd.getBeanClassName();
			try {
				bean = Class.forName(beanClassName);
				methods(bean);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * bean에 속한 method 검색
	 * @param bean
	 */
	private void methods(Class<?> bean) {
		String rootUrl = ((RequestMapping) bean.getAnnotation(RequestMapping.class)).value()[0];
		Method[] methods = bean.getDeclaredMethods();
		Access access = null;
		for (Method method : methods) {
			access  = method.getAnnotation(Access.class);
			if(access != null) {
				annotations(method, rootUrl, access.scope());
			}
		}
	}
	
	/**
	 * method에 속한 annotation 검색
	 * @param method
	 * @param rootUrl
	 * @param scope
	 */
	private void annotations(Method method, String rootUrl, String scope) {
		ApiVO apiVO = new ApiVO();
		apiVO.setScope(scope);
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if(annotation instanceof ApiOperation) {
				apiVO.setNotes( ((ApiOperation) annotation).notes() );
			}else if(annotation instanceof GetMapping) {
				apiVO.setMethod("GET");
				apiVO.setUrl( rootUrl + ( ((GetMapping) annotation)).value()[0] );
			}else if(annotation instanceof PostMapping) {
				apiVO.setMethod("POST");
				apiVO.setUrl( rootUrl + ( ((PostMapping) annotation)).value()[0] );
			}else if(annotation instanceof PutMapping) {
				apiVO.setMethod("PUT");
				apiVO.setUrl( rootUrl + ( ((PutMapping) annotation)).value()[0] );
			}else if(annotation instanceof DeleteMapping) {
				apiVO.setMethod("DELETE");
				apiVO.setUrl( rootUrl + ( ((DeleteMapping) annotation)).value()[0] );
			}
		}
		apiList.add(apiVO);
	}
	
}
