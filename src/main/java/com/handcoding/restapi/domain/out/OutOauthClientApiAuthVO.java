package com.handcoding.restapi.domain.out;

import java.util.List;

import com.handcoding.restapi.domain.ApiVO;
import com.handcoding.restapi.domain.OauthClientDetailsVO;

import io.swagger.annotations.ApiModelProperty;

public class OutOauthClientApiAuthVO extends OauthClientDetailsVO {
	
	@ApiModelProperty(value="API 리스트")
	private List<ApiVO> apiList;

	public List<ApiVO> getApiList() {
		return apiList;
	}
	public void setApiList(List<ApiVO> apiList) {
		this.apiList = apiList;
	}
	@Override
	public String toString() {
		return "OutOauthClientApiAuthVO [apiList=" + apiList + "]";
	}

}
