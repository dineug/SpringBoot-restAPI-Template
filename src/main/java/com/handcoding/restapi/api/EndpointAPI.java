package com.handcoding.restapi.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * endpoint api
 * @author 이승환
 * @version 2018.01.28 v1.0
 */
public abstract class EndpointAPI<T> {
	
	protected static String API_URL = "http://localhost:8080";
	protected Retrofit retrofit;
	
	{
		OkHttpClient client = new OkHttpClient.Builder()
												.readTimeout(5, TimeUnit.SECONDS)
												.connectTimeout(5, TimeUnit.SECONDS)
												.build();
		retrofit = new Retrofit.Builder()
								.baseUrl(API_URL)
								.addConverterFactory(GsonConverterFactory.create())
								.client(client)
								.build();
	}
	
	protected abstract T create();
	
}
