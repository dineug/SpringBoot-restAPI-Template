package com.handcoding.restapi.domain.out;

import java.util.List;

import com.handcoding.restapi.domain.ApiVO;
import com.handcoding.restapi.domain.OauthClientDetailsVO;

import io.swagger.annotations.ApiModelProperty;

public class OutOauthClientApiAuthVO extends OauthClientDetailsVO {
	
	@ApiModelProperty(value="API 리스트")
	private List<ApiVO> apiVO;

	public List<ApiVO> getApiVO() {
		return apiVO;
	}
	public void setApiVO(List<ApiVO> apiVO) {
		this.apiVO = apiVO;
	}
	@Override
	public String toString() {
		return "OutOauthClientApiAuthVO [apiVO=" + apiVO + "]";
	}
}
