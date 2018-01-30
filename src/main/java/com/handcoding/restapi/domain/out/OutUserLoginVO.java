package com.handcoding.restapi.domain.out;

import java.util.concurrent.TimeUnit;

import com.handcoding.restapi.domain.UserVO;

import io.swagger.annotations.ApiModelProperty;

public class OutUserLoginVO extends UserVO {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="token 시간")
	private int timeout;
	@ApiModelProperty(value="token 시간단위")
	private TimeUnit timeUnit;
	
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	@Override
	public String toString() {
		return "OutUserLoginVO [timeout=" + timeout + ", timeUnit=" + timeUnit + "]";
	}
}
