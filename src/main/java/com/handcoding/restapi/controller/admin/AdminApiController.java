package com.handcoding.restapi.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.annotation.Access;
import com.handcoding.restapi.component.ServiceComponent;
import com.handcoding.restapi.domain.ApiClientAuthVO;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.SearchVO;
import com.handcoding.restapi.domain.out.OutOauthClientApiAuthVO;

import io.swagger.annotations.ApiOperation;

/**
 * client API 리소스 API
 * @author 이승환
 * @version 2018.02.13 v1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminApiController {
	
	@Autowired
	private ServiceComponent service;
	
	/**
	 * client List 개별 API 리스트 정보 조회
	 * @param searchVO
	 * @return
	 */
	@ApiOperation(value="", notes = "client List 개별 API 리스트 정보 조회")
	@Access(scope="private")
	@GetMapping("/v1.0/clients")
	public ResponseVO<List<OutOauthClientApiAuthVO>> clientList(SearchVO searchVO) {
		ResponseVO<List<OutOauthClientApiAuthVO>> responseVO = new ResponseVO<>();
		List<OutOauthClientApiAuthVO> oauthClientApiAuthList = service.getApiService().oauthClientApiAuthVOList(searchVO);
		responseVO.setResponse(oauthClientApiAuthList);
		if(oauthClientApiAuthList.size() == 0) {
			responseVO.setCheck(false);
		}
		return responseVO;
	}
	
	/**
	 * client API scope 변경
	 * @param apiClientAuthVO
	 * @return
	 */
	@ApiOperation(value="", notes = "client API scope 변경")
	@Access(scope="private")
	@PutMapping("/v1.0/clients/api")
	public ResponseVO<Object> clientApiUpdate(@RequestBody ApiClientAuthVO apiClientAuthVO) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		boolean check = service.getApiService().clientApiUpdate(apiClientAuthVO);
		if(!check) {
			responseVO.setCheck(check);
		}
		return responseVO;
	}
	
	
}
