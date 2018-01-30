package com.handcoding.restapi.controller.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.annotation.User;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.UserVO;

/**
 * 사용자 로그인 시스템 API
 * @author 이승환
 * @version 2018.01.30 v1.0
 */
@RestController
@RequestMapping("/system")
public class SystemLoginController {
	
	/**
	 * 사용자 정보
	 * @param token
	 * @return
	 */
	@GetMapping("/v1.0/users/token")
	public ResponseVO<UserVO> getUser(@User UserVO user) {
		ResponseVO<UserVO> responseVO = new ResponseVO<>();
		responseVO.setResponse(user);
		return responseVO;
	}
	
}
