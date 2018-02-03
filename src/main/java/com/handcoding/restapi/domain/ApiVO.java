package com.handcoding.restapi.domain;

import io.swagger.annotations.ApiModelProperty;

public class ApiVO {
	
	@ApiModelProperty(value="API 타입")
	private String apiTypeCode = "RESTAPI_01";
	@ApiModelProperty(value="API URL")
	private String url;
	@ApiModelProperty(value="API 메소드 타입")
	private String method;
	@ApiModelProperty(value="API 설명")
	private String notes;
	@ApiModelProperty(value="API 호출범위")
	private String scope;
	
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "ApiVO [apiTypeCode=" + apiTypeCode + ", url=" + url + ", method=" + method + ", notes=" + notes
				+ ", scope=" + scope + "]";
	}
}
