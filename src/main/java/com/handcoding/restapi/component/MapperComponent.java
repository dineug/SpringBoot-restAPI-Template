package com.handcoding.restapi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.handcoding.restapi.mapper.CodeMapper;
import com.handcoding.restapi.mapper.CommonMapper;
import com.handcoding.restapi.mapper.UserSignUpMapper;

/**
 * Mapper 컴포넌트
 * @author 이승환
 * @version 2018.01.25 v1.0
 */
@Component
public class MapperComponent {
	
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private CodeMapper codeMapper;
	@Autowired
	private UserSignUpMapper userSignUpMapper;

	
	
	public CommonMapper getCommonMapper() {
		return commonMapper;
	}
	public CodeMapper getCodeMapper() {
		return codeMapper;
	}
	public UserSignUpMapper getUserSignUpMapper() {
		return userSignUpMapper;
	}
	
}
