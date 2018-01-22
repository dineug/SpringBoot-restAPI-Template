package com.handcoding.restapi.domain;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class EmailConfirmVO {
	
	@ApiModelProperty(value="키값")
	private String emailKey;
	@ApiModelProperty(value="ID")
	private String id;
	@ApiModelProperty(value="사용자 타입")
	private String userTypeCode = "NORMAL";
	@ApiModelProperty(value="등록일")
	private Date regDate;
	@ApiModelProperty(value="유효일")
	private Date expiredDate;
	
	public String getEmailKey() {
		return emailKey;
	}
	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserTypeCode() {
		return userTypeCode;
	}
	/**
	 * code : 0 - 사용자 default<br>
	 * code : 1 - 관리자<br>
	 * @return code
	 */
	public int getUserTypeCodeInt() {
		int code = 0;
		if(userTypeCode.equals("NORMAL")) {
			code = 0;
		}else if(userTypeCode.equals("ADMIN")) {
			code = 1;
		}
		return code;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	@Override
	public String toString() {
		return "EmailConfirmVO [emailKey=" + emailKey + ", id=" + id + ", userTypeCode=" + userTypeCode + ", regDate="
				+ regDate + ", expiredDate=" + expiredDate + "]";
	}
}
