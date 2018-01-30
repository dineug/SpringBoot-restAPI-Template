package com.handcoding.restapi.service;

import com.handcoding.restapi.domain.in.InUserLoginVO;
import com.handcoding.restapi.domain.out.OutUserLoginVO;

/**
 * 로그인 서비스
 * @author 이승환
 * @version 2018.01.30 v1.0
 */
public interface UserLoginService {
	
	/**
	 * 로그인
	 * @param userVO
	 * @return
	 */
	public OutUserLoginVO login(InUserLoginVO inUserLoginVO);
	
}
