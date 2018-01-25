package com.handcoding.restapi.service;

import com.handcoding.restapi.domain.EmailConfirmVO;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.in.InUserSignUpVO;

/**
 * 회원가입 서비스
 * @author 이승환
 * @version 2018.01.23 v1.0
 */
public interface UserSignUpService {
	
	/**
	 * 회원가입 및 이메일 인증메일 발송
	 * @param inUserSignUpVO
	 */
	public void userSignUpEmail(InUserSignUpVO inUserSignUpVO) throws Exception;
	
	/**
	 * 이메일 인증
	 * @param emailConfirmVO
	 * @return
	 */
	public ResponseVO<Object> userEmailConfirm(EmailConfirmVO emailConfirmVO) throws Exception;
	
}
