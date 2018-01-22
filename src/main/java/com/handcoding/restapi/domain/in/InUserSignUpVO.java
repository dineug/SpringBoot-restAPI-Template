package com.handcoding.restapi.domain.in;

import com.handcoding.restapi.domain.UserVO;

import io.swagger.annotations.ApiModelProperty;

public class InUserSignUpVO extends UserVO {
	
	@ApiModelProperty(value="이메일인증 URL")
	private String emailConfirmUrl;
	
	public String getEmailConfirmUrl() {
		return emailConfirmUrl;
	}
	public void setEmailConfirmUrl(String emailConfirmUrl) {
		this.emailConfirmUrl = emailConfirmUrl;
	}
	@Override
	public String toString() {
		return "InUserSignUpVO [emailConfirmUrl=" + emailConfirmUrl + "]";
	}
}
