package com.handcoding.restapi.domain;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class UserVO extends CommonVO {
	
	@ApiModelProperty(value="ID")
	private String id;
	@ApiModelProperty(value="암호")
	private String pw;
	@ApiModelProperty(value="이름")
	private String name;
	@ApiModelProperty(value="이메일")
	private String email;
	@ApiModelProperty(value="사용자 타입")
	private String typeCode;
	@ApiModelProperty(value="사용자 상태")
	private String statusCode;
	@ApiModelProperty(value="생성일")
	private Date regDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", typeCode=" + typeCode
				+ ", statusCode=" + statusCode + ", regDate=" + regDate + "]";
	}
}
