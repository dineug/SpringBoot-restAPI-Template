package com.handcoding.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handcoding.restapi.component.MapperComponent;
import com.handcoding.restapi.domain.ApiClientAuthVO;
import com.handcoding.restapi.domain.ApiVO;
import com.handcoding.restapi.domain.OauthClientDetailsVO;
import com.handcoding.restapi.domain.SearchVO;
import com.handcoding.restapi.domain.out.OutOauthClientApiAuthVO;

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
		SearchVO searchVO = new SearchVO();
		List<OauthClientDetailsVO> oauthClientDetailsList = mapper.getApiMapper().oauthClientDetailsList(searchVO);
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
	
	// client List 개별 API 리스트 정보 조회
	@Override
	public List<OutOauthClientApiAuthVO> oauthClientApiAuthVOList(SearchVO searchVO) {
		List<OutOauthClientApiAuthVO> oauthClientApiAuthList = new ArrayList<>();
		OutOauthClientApiAuthVO outOauthClientApiAuthVO = null;
		ApiClientAuthVO apiClientAuthVO = new ApiClientAuthVO();
		
		List<OauthClientDetailsVO> oauthClientDetailsList = mapper.getApiMapper().oauthClientDetailsList(searchVO);
		
		// 페이징 total값 세팅
		int total = mapper.getCommonMapper().pagingTotal();
		for (OauthClientDetailsVO temp : oauthClientDetailsList) {
			temp.setTotal(total);
		}
		
		for (OauthClientDetailsVO temp : oauthClientDetailsList) {
			
			// client 정보 셋팅
			outOauthClientApiAuthVO = new OutOauthClientApiAuthVO();
			outOauthClientApiAuthVO.setClient_id(temp.getClient_id());
			outOauthClientApiAuthVO.setScope(temp.getScope());
			outOauthClientApiAuthVO.setId(temp.getId());
			outOauthClientApiAuthVO.setTotal(temp.getTotal());
			
			// client API 정보 셋팅
			apiClientAuthVO.setClient_id(temp.getClient_id());
			outOauthClientApiAuthVO.setApiVO(mapper.getApiMapper().clientApiList(apiClientAuthVO));
			
			// 리스트에 추가
			oauthClientApiAuthList.add(outOauthClientApiAuthVO);
		}
		return oauthClientApiAuthList;
	}

}
