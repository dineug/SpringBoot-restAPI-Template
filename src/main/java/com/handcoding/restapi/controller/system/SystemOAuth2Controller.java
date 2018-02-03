package com.handcoding.restapi.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.domain.OAuth2TokenVO;
import com.handcoding.restapi.domain.OAuth2VO;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.exception.AccessTokenBadRequestException;

import io.swagger.annotations.ApiOperation;

/**
 * API OAuth2 access_token
 * @author 이승환
 * @version 2018.01.30 v1.0
 */
@RestController
@RequestMapping("/system")
public class SystemOAuth2Controller {
	
	@Autowired
	private CommonComponent common;
	
	/**
	 * accessToken 발급
	 * @param token
	 * @return
	 * @throws AccessTokenBadRequestException
	 */
	@ApiOperation(value="", notes = "accessToken 발급")
	@PostMapping("/oauth/token")
	public ResponseVO<OAuth2TokenVO> getAccessToken(@RequestBody OAuth2VO oAuth2VO) throws Exception {
		ResponseVO<OAuth2TokenVO> responseVO = new ResponseVO<>();
		OAuth2TokenVO oAuth2TokenVO = common.getoAuth2API().getAccessToken(oAuth2VO);
		if(oAuth2TokenVO == null) throw new AccessTokenBadRequestException();
		responseVO.setResponse(oAuth2TokenVO);
		return responseVO;
	}
	
}
