package com.handcoding.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handcoding.restapi.component.MapperComponent;
import com.handcoding.restapi.domain.ApiClientAuthVO;
import com.handcoding.restapi.domain.ApiVO;
import com.handcoding.restapi.domain.OauthClientDetailsVO;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ApiServiceImpl implements ApiService {
	
	@Autowired
	private MapperComponent mapper;
	
	// API 초기화
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName="Exception")
	public void apiInit(List<ApiVO> apiList) {
		ApiVO apiVO = new ApiVO();
		
		// API 테이블 초기화
		mapper.getApiMapper().apiDeleteAll(apiVO);
		mapper.getApiMapper().apiInsertAll(apiList);
		
		// client API 정보 추가
		List<OauthClientDetailsVO> oauthClientDetailsList = mapper.getApiMapper().oauthClientDetailsList();
		ApiClientAuthVO apiClientAuthVO = new ApiClientAuthVO();
		for (OauthClientDetailsVO temp : oauthClientDetailsList) {
			apiClientAuthVO.setClient_id(temp.getClient_id());
			mapper.getApiMapper().apiClientAuthInsertAll(apiClientAuthVO);
		}
		
		// client API 정보 삭제
		List<ApiVO> apiDelList = mapper.getApiMapper().apiDelList(apiVO);
		for (ApiVO temp : apiDelList) {
			mapper.getApiMapper().apiClientAuthDelete(temp);
		}
		
		// client API 정보 업데이트
		List<ApiClientAuthVO> apiClientAuthList = null;
		for (OauthClientDetailsVO temp : oauthClientDetailsList) {
			apiClientAuthVO.setClient_id(temp.getClient_id());
			apiClientAuthList = mapper.getApiMapper().apiClientAuthUpdateList(apiClientAuthVO);
			for (ApiClientAuthVO temp2 : apiClientAuthList) {
				mapper.getApiMapper().apiClientAuthUpdate(temp2);
			}
		}
	}
	
	// client API 정보 조회
	@Override
	public List<ApiClientAuthVO> apiClientAuthList(ApiClientAuthVO apiClientAuthVO) {
		return mapper.getApiMapper().apiClientAuthList(apiClientAuthVO);
	}

}
