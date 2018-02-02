package com.handcoding.restapi.domain;

import java.util.List;

/**
 * OAuth2.0 token check VO
 * @author 이승환
 * @version 2018.02.02 v1.0
 */
public class OAuth2CheckTokenVO {
	
	private List<String> scope;
	private int exp;
	private List<String> authorities;
	private String client_id;
	
	public List<String> getScope() {
		return scope;
	}
	public void setScope(List<String> scope) {
		this.scope = scope;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	@Override
	public String toString() {
		return "OAuth2CheckTokenVO [scope=" + scope + ", exp=" + exp + ", authorities=" + authorities + ", client_id="
				+ client_id + "]";
	}
}
