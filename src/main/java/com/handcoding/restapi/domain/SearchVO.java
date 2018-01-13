package com.handcoding.restapi.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 검색 도메인
 * @author 이승환
 * @version 2018.01.13 v1.0
 */
public class SearchVO {
	
	@ApiModelProperty(value="검색범위")
	private String scope;
	@ApiModelProperty(value="검색 키워드")
	private String keyword;
	@ApiModelProperty(value="정렬 쿼리")
	private String orderBy;
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	@Override
	public String toString() {
		return "SearchVO [scope=" + scope + ", keyword=" + keyword + ", orderBy=" + orderBy + "]";
	}
}
