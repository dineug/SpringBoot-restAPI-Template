package com.handcoding.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handcoding.restapi.bean.HandlerAsync;
import com.handcoding.restapi.bean.TempKey;
import com.handcoding.restapi.domain.EmailConfirmVO;
import com.handcoding.restapi.domain.in.InEmailConfirmSendVO;
import com.handcoding.restapi.domain.in.InUserSignUpVO;
import com.handcoding.restapi.mapper.UserSignUpMapper;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserSignUpServiceImpl implements UserSignUpService {
	
	@Autowired
	private UserSignUpMapper userSignUpMapper;
	
	@Autowired
	private HandlerAsync async;
	
	// 회원가입 및 이메일 인증메일 발송
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName="Exception")
	public void userSignUpEmail(InUserSignUpVO inUserSignUpVO) throws Exception {
		userSignUpMapper.userInsert(inUserSignUpVO);
		
		TempKey tempKey = new TempKey();
		String key = tempKey.getKey(50);
		EmailConfirmVO emailConfirmVO = new EmailConfirmVO();
		emailConfirmVO.setId(inUserSignUpVO.getId());
		emailConfirmVO.setUserTypeCode(inUserSignUpVO.getUserTypeCodeInt());
		emailConfirmVO.setEmailKey(key);
		userSignUpMapper.emailConfirmInsert(emailConfirmVO);
		
		InEmailConfirmSendVO inEmailConfirmSendVO = new InEmailConfirmSendVO();
		inEmailConfirmSendVO.setId(inUserSignUpVO.getId());
		inEmailConfirmSendVO.setUserTypeCode(inUserSignUpVO.getUserTypeCodeInt());
		inEmailConfirmSendVO.setEmail(inUserSignUpVO.getEmail());
		inEmailConfirmSendVO.setEmailKey(key);
		inEmailConfirmSendVO.setEmailConfirmUrl(inUserSignUpVO.getEmailConfirmUrl());
		async.signUpEmailConfirm(inEmailConfirmSendVO);
	}

}
