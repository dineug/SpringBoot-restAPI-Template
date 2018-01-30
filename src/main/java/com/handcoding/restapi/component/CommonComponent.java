package com.handcoding.restapi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.handcoding.restapi.bean.EnvironmentConfig;
import com.handcoding.restapi.bean.HandlerAsync;
import com.handcoding.restapi.bean.HandlerToken;

/**
 * 공통 컴포넌트
 * @author 이승환
 * @version 2018.01.25 v1.0
 */
@Component
public class CommonComponent {
	
	@Autowired
	private HandlerAsync async;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MessageSource msg;
	@Autowired
	private EnvironmentConfig config;
	@Autowired
	private HandlerToken handlerToken;
	
	public HandlerAsync getAsync() {
		return async;
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	public MessageSource getMsg() {
		return msg;
	}
	public EnvironmentConfig getConfig() {
		return config;
	}
	public HandlerToken getHandlerToken() {
		return handlerToken;
	}
	
}
