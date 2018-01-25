package com.handcoding.restapi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.handcoding.restapi.service.CodeService;
import com.handcoding.restapi.service.UserSignUpService;

/**
 * Service 컴포넌트
 * @author 이승환
 * @version 2018.01.25 v1.0
 */
@Component
public class ServiceComponent {
	
	@Autowired
	private CodeService codeService;
	@Autowired
	private UserSignUpService userSignUpService;
	
	
	
	public CodeService getCodeService() {
		return codeService;
	}
	public UserSignUpService getUserSignUpService() {
		return userSignUpService;
	}
	
}
