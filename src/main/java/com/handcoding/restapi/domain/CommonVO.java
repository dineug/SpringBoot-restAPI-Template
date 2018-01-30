package com.handcoding.restapi.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 공통 도메인
 * @author 이승환
 * @version 2018.01.28 v1.1
 */
public class CommonVO {
	
	@ApiModelProperty(value="페이징 총갯수")
	private int total;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
