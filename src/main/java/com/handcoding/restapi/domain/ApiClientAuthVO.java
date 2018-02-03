package com.handcoding.restapi.domain;

import io.swagger.annotations.ApiModelProperty;

public class ApiClientAuthVO {
	
	@ApiModelProperty(value="client 아이디")
	private String client_id;
	@ApiModelProperty(value="API 타입")
	private String apiTypeCode = "RESTAPI_01";
	@ApiModelProperty(value="API URL")
	private String url;
	@ApiModelProperty(value="API 메소드 타입")
	private String method;
	@ApiModelProperty(value="API 호출범위")
	private String scope;
	
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getApiTypeCode() {
		return apiTypeCode;
	}
	public void setApiTypeCode(String apiTypeCode) {
		this.apiTypeCode = apiTypeCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "ApiClientAuthVO [client_id=" + client_id + ", apiTypeCode=" + apiTypeCode + ", url=" + url + ", method="
				+ method + ", scope=" + scope + "]";
	}
}
