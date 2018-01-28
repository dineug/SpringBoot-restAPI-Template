package com.handcoding.restapi.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * application.properties
 * @author 이승환
 * @version 2018.01.28 v1.0
 */
@Component
public class EnvironmentConfig {
	
	@Value("${handcoding.api.key}")
	private String apiKey;

	public String getApiKey() {
		return apiKey;
	}
	
}
