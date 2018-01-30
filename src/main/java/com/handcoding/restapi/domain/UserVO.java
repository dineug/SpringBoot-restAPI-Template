package com.handcoding.restapi.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class UserVO extends CommonVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="ID")
	private String id;
	@ApiModelProperty(value="암호")
	private String pw;
	@ApiModelProperty(value="이름")
	private String name;
	@ApiModelProperty(value="이메일")
	private String email;
	@ApiModelProperty(value="사용자 타입")
	private String userTypeCode = "NORMAL";
	@ApiModelProperty(value="사용자 상태")
	private String statusCode = "EMAIL_CONFIRM";
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
	public String getUserTypeCode() {
		return userTypeCode;
	}
	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
	/**
	 * code : 0 - 사용자 default<br>
	 * code : 1 - 관리자<br>
	 * @param code
	 */
	public void setUserTypeCode(int code) {
		switch (code) {
		case 0:
			this.userTypeCode = "NORMAL";
			break;
		case 1:
			this.userTypeCode = "ADMIN";
			break;
		}
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * code : 0 - 이메일인증대기 default<br>
	 * code : 1 - 사용가능<br>
	 * code : 2 - 탈퇴<br>
	 * @param code
	 */
	public void setStatusCode(int code) {
		switch (code) {
		case 0:
			this.statusCode = "EMAIL_CONFIRM";
			break;
		case 1:
			this.statusCode = "USE";
			break;
		case 2:
			this.statusCode = "LEAVE";
			break;
		}
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", email=" + email + ", userTypeCode="
				+ userTypeCode + ", statusCode=" + statusCode + ", regDate=" + regDate + "]";
	}
}
