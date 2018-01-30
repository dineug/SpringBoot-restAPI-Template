package com.handcoding.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.component.MapperComponent;
import com.handcoding.restapi.domain.EmailConfirmVO;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.in.InEmailConfirmSendVO;
import com.handcoding.restapi.domain.in.InUserSignUpVO;
import com.handcoding.restapi.util.TempKey;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserSignUpServiceImpl implements UserSignUpService {
	
	@Autowired
	private MapperComponent mapper;
	
	@Autowired
	private CommonComponent common;
	
	
	// 회원가입 및 이메일 인증메일 발송
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName="Exception")
	public void userSignUpEmail(InUserSignUpVO inUserSignUpVO) throws Exception {
		// 사용자 정보 입력
		mapper.getUserSignUpMapper().userInsert(inUserSignUpVO);
		
		// 사용자 이메일 인증 정보처리
		TempKey tempKey = new TempKey();
		String key = tempKey.getKey(50);
		EmailConfirmVO emailConfirmVO = new EmailConfirmVO();
		emailConfirmVO.setId(inUserSignUpVO.getId());
		emailConfirmVO.setUserTypeCode(inUserSignUpVO.getUserTypeCode());
		emailConfirmVO.setEmailKey(key);
		mapper.getUserSignUpMapper().emailConfirmInsert(emailConfirmVO);
		
		// 사용자 인증메일 발송
		InEmailConfirmSendVO inEmailConfirmSendVO = new InEmailConfirmSendVO();
		inEmailConfirmSendVO.setEmail(inUserSignUpVO.getEmail());
		inEmailConfirmSendVO.setEmailKey(key);
		inEmailConfirmSendVO.setEmailConfirmUrl(inUserSignUpVO.getEmailConfirmUrl());
		common.getAsync().signUpEmailConfirm(inEmailConfirmSendVO);
	}
	
	// 이메일 인증
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName="Exception")
	public ResponseVO<Object> userEmailConfirm(EmailConfirmVO emailConfirmVO) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		// 사용자 상태값 변경
		int checkNum = mapper.getUserSignUpMapper().emailConfirmUpdate(emailConfirmVO);
		boolean check = checkNum > 0;
		if(!check) {
			responseVO.setCheck(check);
			// 실패 이유 체크
			checkNum = mapper.getUserSignUpMapper().emailConfirmKeyCheck(emailConfirmVO);
			responseVO.setCode(checkNum);
		}else {
			// 성공시 키 삭제
			mapper.getUserSignUpMapper().emailConfirmKeyDelete(emailConfirmVO);
		}
		return responseVO;
	}
	
}
