package com.handcoding.restapi.service;

import java.util.List;

import com.handcoding.restapi.domain.ApiClientAuthVO;
import com.handcoding.restapi.domain.ApiVO;

/**
 * API 서비스
 * @author 이승환
 * @version 2018.02.04 v1.0
 */
public interface ApiService {
	
	/**
	 * API 초기화
	 * @param apiList
	 */
	public void apiInit(List<ApiVO> apiList);
	
	/**
	 * client API 정보 조회
	 * @param apiClientAuthVO
	 * @return
	 */
	public List<ApiClientAuthVO> apiClientAuthList(ApiClientAuthVO apiClientAuthVO);
	
}
