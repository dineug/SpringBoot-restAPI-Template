package com.handcoding.restapi.domain.in;

import com.handcoding.restapi.domain.CodeVO;

import io.swagger.annotations.ApiModelProperty;

public class InCodeUpdateVO extends CodeVO{
	
	@ApiModelProperty(value="수정할 코드값")
	private String newCode;
	@ApiModelProperty(value="수정할 상위 코드값")
	private String newParentCode;
	
	public String getNewCode() {
		return newCode;
	}
	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}
	public String getNewParentCode() {
		return newParentCode;
	}
	public void setNewParentCode(String newParentCode) {
		this.newParentCode = newParentCode;
	}
	@Override
	public String toString() {
		return "InCodeUpdateVO [newCode=" + newCode + ", newParentCode=" + newParentCode + "]";
	}
}
