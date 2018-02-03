package com.handcoding.restapi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.annotation.Access;
import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.component.ServiceComponent;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.in.InUserLoginVO;
import com.handcoding.restapi.domain.out.OutUserLoginVO;

import io.swagger.annotations.ApiOperation;

/**
 * 관리자 로그인 API
 * @author 이승환
 * @version 2018.02.02 v1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController {
	
	@Autowired
	private CommonComponent common;
	
	@Autowired
	private ServiceComponent service;
	
	/**
	 * 관리자 로그인
	 * @param id
	 * @param InUserLoginVO
	 * @return
	 */
	@ApiOperation(value="", notes = "관리자 로그인")
	@Access(scope="private")
	@PostMapping("/v1.0/users/{id}/login")
	public ResponseVO<String> login(@PathVariable String id, @RequestBody InUserLoginVO inUserLoginVO) {
		ResponseVO<String> responseVO = new ResponseVO<>();
		String token = null;
		inUserLoginVO.setId(id);
		inUserLoginVO.setUserTypeCode(1);
		OutUserLoginVO outUserLoginVO = service.getUserLoginService().login(inUserLoginVO);
		if(outUserLoginVO != null) {
			outUserLoginVO.setTimeout(inUserLoginVO.getTimeout());
			outUserLoginVO.setTimeUnit(inUserLoginVO.getTimeUnit());
			token = common.getHandlerToken().getToken(outUserLoginVO);
		}else {
			responseVO.setCheck(false);
		}
		responseVO.setResponse(token);
		return responseVO;
	}
	
}
