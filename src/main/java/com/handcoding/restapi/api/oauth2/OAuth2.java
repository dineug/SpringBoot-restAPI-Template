package com.handcoding.restapi.api.oauth2;

import java.util.Map;

import com.handcoding.restapi.domain.OAuth2CheckTokenVO;
import com.handcoding.restapi.domain.OAuth2TokenVO;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * OAuth2.0 API
 * @author 이승환
 * @version 2018.02.02 v1.0
 */
public interface OAuth2 {
	
	/**
	 * AccessToken 생성
	 * @param oAuth2VO
	 * @return
	 */
	@FormUrlEncoded
	@POST("/oauth/token")
	public Call<OAuth2TokenVO> getAccessToken(@Header("authorization") String authorization, @FieldMap Map<String, String> fields);
	
	/**
	 * AccessToken 체크
	 * @param token
	 * @return
	 */
	@GET("/oauth/check_token")
	public Call<OAuth2CheckTokenVO> checkAccessToken(@Header("authorization") String authorization, @Query("token") String accessToken);
	
}
