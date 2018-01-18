package com.handcoding.restapi.domain;

import io.swagger.annotations.ApiModelProperty;

public class CodeVO extends CommonVO {
	
	@ApiModelProperty(value="코드값")
	private String code;
	@ApiModelProperty(value="상위 코드값")
	private String parentCode = "";
	@ApiModelProperty(value="코드에 대한 설명")
	private String descript;
	
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
	@Override
	public String toString() {
		return "CodeVO [code=" + code + ", parentCode=" + parentCode + ", descript=" + descript + "]";
	}
}
