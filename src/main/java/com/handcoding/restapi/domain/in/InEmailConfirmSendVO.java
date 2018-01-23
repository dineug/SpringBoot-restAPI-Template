package com.handcoding.restapi.domain.in;

import com.handcoding.restapi.domain.EmailConfirmVO;

import io.swagger.annotations.ApiModelProperty;

public class InEmailConfirmSendVO extends EmailConfirmVO {
	
	@ApiModelProperty(value="이메일")
	private String email;
	@ApiModelProperty(value="이메일인증 URL")
	private String emailConfirmUrl;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailConfirmUrl() {
		return emailConfirmUrl;
	}
	public void setEmailConfirmUrl(String emailConfirmUrl) {
		this.emailConfirmUrl = emailConfirmUrl;
	}
	@Override
	public String toString() {
		return "InEmailConfirmSendVO [email=" + email + ", emailConfirmUrl=" + emailConfirmUrl + "]";
	}
}
