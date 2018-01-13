package com.handcoding.restapi.domain;

import io.swagger.annotations.ApiModelProperty;

public class CodeVO extends CommonVO {
	
	@ApiModelProperty(value="코드값")
	private String code;
	@ApiModelProperty(value="상위 코드값")
	private String parentCode = "";
	@ApiModelProperty(value="코드에 대한 설명")
	private String descript;
	
	@ApiModelProperty(value="수정할 코드값")
	private String newCode;
	@ApiModelProperty(value="수정할 상위 코드값")
	private String newParentCode;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
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
		return "CodeVO [code=" + code + ", parentCode=" + parentCode + ", descript=" + descript + ", newCode=" + newCode
				+ ", newParentCode=" + newParentCode + "]";
	}
}
