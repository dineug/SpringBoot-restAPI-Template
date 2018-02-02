package com.handcoding.restapi.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.api.oauth2.OAuth2API;
import com.handcoding.restapi.domain.OAuth2TokenVO;
import com.handcoding.restapi.domain.OAuth2VO;
import com.handcoding.restapi.domain.ResponseVO;

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
	private OAuth2API oAuth2API;
	
	/**
	 * accessToken 발급
	 * @param token
	 * @return
	 */
	@ApiOperation(value="", notes = "accessToken 발급")
	@PostMapping("/oauth/token")
	public ResponseVO<OAuth2TokenVO> getAccessToken(@RequestBody OAuth2VO oAuth2VO) {
		ResponseVO<OAuth2TokenVO> responseVO = new ResponseVO<>();
		OAuth2TokenVO oAuth2TokenVO = oAuth2API.getAccessToken(oAuth2VO);
		responseVO.setResponse(oAuth2TokenVO);
		return responseVO;
	}
	
}
