package com.handcoding.restapi.controller.system;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.component.ServiceComponent;
import com.handcoding.restapi.domain.EmailConfirmVO;
import com.handcoding.restapi.domain.ResponseVO;

import io.swagger.annotations.ApiOperation;

/**
 * 사용자 회원가입 시스템 API
 * @author 이승환
 * @version 2018.01.26 v1.0
 */
@RestController
@RequestMapping("/system")
public class SystemSignUpController {
	
	@Autowired
	private CommonComponent common;
	
	@Autowired
	private ServiceComponent service;
	
	/**
	 * 사용자 이메일인증
	 * @param locale
	 * @param emailConfirmVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "사용자 이메일인증")
	@PutMapping("/v1.0/users/emailconfirm")
	public ResponseVO<Object> emailConfirm(Locale locale, @RequestBody EmailConfirmVO emailConfirmVO) {
		ResponseVO<Object> responseVO = null;
		String message = null;
		responseVO = service.getUserSignUpService().userEmailConfirm(emailConfirmVO);
		if(responseVO.isCheck()) {
			message = common.getMsg().getMessage("success", new String[] {"E-mail authentication"}, locale);
		}else {
			if(responseVO.getCode() == 0) {
				message = common.getMsg().getMessage("no_key", new String[] {"emailKey"}, locale);
			}else if(responseVO.getCode() == 1) {
				message = common.getMsg().getMessage("no_validity", new String[] {"emailKey"}, locale);
			}
		}
		responseVO.setMessage(message);
		return responseVO;
	}
	
}
