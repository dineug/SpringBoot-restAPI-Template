package com.handcoding.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handcoding.restapi.component.MapperComponent;
import com.handcoding.restapi.domain.in.InUserLoginVO;
import com.handcoding.restapi.domain.out.OutUserLoginVO;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserLoginServiceImpl implements UserLoginService {
	
	@Autowired
	private MapperComponent mapper;
	
	// 로그인
	@Override
	public OutUserLoginVO login(InUserLoginVO inUserLoginVO) {
		return mapper.getUserLoginMapper().login(inUserLoginVO);
	}

}
