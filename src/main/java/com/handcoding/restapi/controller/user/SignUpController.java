package com.handcoding.restapi.controller.user;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.component.ServiceComponent;
import com.handcoding.restapi.domain.EmailConfirmVO;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.in.InUserSignUpVO;

import io.swagger.annotations.ApiOperation;

/**
 * 기본사용자 회원가입 API
 * @author 이승환
 * @version 2018.01.23 v1.1
 */
@RestController
@RequestMapping("/normal")
public class SignUpController {
	
	@Autowired
	private CommonComponent common;
	
	@Autowired
	private ServiceComponent service;
	
	/**
	 * 기본사용자 회원가입
	 * @param inUserSignUpVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "기본사용자 회원가입")
	@PostMapping("/v1.0/users")
	public ResponseVO<Object> signUp(@RequestBody InUserSignUpVO inUserSignUpVO) throws Exception {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		service.getUserSignUpService().userSignUpEmail(inUserSignUpVO);
		return responseVO;
	}
	
	/**
	 * 기본사용자 이메일인증
	 * @param locale
	 * @param emailConfirmVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "기본사용자 이메일인증")
	@PutMapping("/v1.0/users/emailconfirm")
	public ResponseVO<Object> emailConfirm(Locale locale, @RequestBody EmailConfirmVO emailConfirmVO) throws Exception {
		ResponseVO<Object> responseVO = null;
		String message = null;
		responseVO = service.getUserSignUpService().userEmailConfirm(emailConfirmVO);
		if(responseVO.isCheck()) {
			message = common.getMsg().getMessage("success", new String[] {"E-mail authentication"}, locale);
		}else {
			if(responseVO.getCode() == 0) {
				message = common.getMsg().getMessage("no_key", new String[] {"emailKey"}, locale);
			}else {
				message = common.getMsg().getMessage("no_validity", new String[] {"emailKey"}, locale);
			}
		}
		responseVO.setMessage(message);
		return responseVO;
	}
	
}
