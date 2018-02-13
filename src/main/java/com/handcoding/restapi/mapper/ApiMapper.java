package com.handcoding.restapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.handcoding.restapi.domain.ApiClientAuthVO;
import com.handcoding.restapi.domain.ApiVO;
import com.handcoding.restapi.domain.OauthClientDetailsVO;
import com.handcoding.restapi.domain.SearchVO;

/**
 * api mapper
 * @author 이승환
 * @version 2018.02.04 v1.0
 */
public interface ApiMapper {
	
	/**
	 * API 정보 등록
	 * @param apiList
	 * @return
	 */
	public void apiInsertAll(List<ApiVO> apiList);
	
	/**
	 * API 정보 삭제
	 * @param apiVO
	 * @return
	 */
	@Delete("delete from api where apiTypeCode = #{apiTypeCode}")
	public int apiDeleteAll(ApiVO apiVO);
	
	/**
	 * client 정보 조회
	 * @return
	 */
	public List<OauthClientDetailsVO> oauthClientDetailsList(SearchVO searchVO);
	
	/**
	 * 추가된 API 정보 client에 등록
	 * @param apiClientAuthVO
	 */
	@Insert("insert into api_client_auth\r\n" + 
			"select #{client_id} client_id, a.apiTypeCode, a.url, a.method, a.scope\r\n" + 
			"from api a left join api_client_auth aca\r\n" + 
			"on a.apiTypeCode = aca.apiTypeCode\r\n" + 
			"and a.url = aca.url\r\n" + 
			"and a.method = aca.method\r\n" + 
			"and a.apiTypeCode = #{apiTypeCode}\r\n" + 
			"and aca.client_id = #{client_id}\r\n" + 
			"where aca.url is null")
	public void apiClientAuthInsertAll(ApiClientAuthVO apiClientAuthVO);
	
	/**
	 * 삭제할 API 정보 조회
	 * @param apiVO
	 * @return
	 */
	@Select("select distinct apiTypeCode, url, method \r\n" + 
			"from api_client_auth \r\n" + 
			"where apiTypeCode = #{apiTypeCode} \r\n" + 
			"and url not in (select url from api where apiTypeCode = #{apiTypeCode}) \r\n" + 
			"and method not in (select method from api where apiTypeCode = #{apiTypeCode})")
	public List<ApiVO> apiDelList(ApiVO apiVO);
	
	/**
	 * API 정보 삭제처리
	 * @param apiVO
	 * @return
	 */
	@Delete("delete from api_client_auth where apiTypeCode = #{apiTypeCode} and url = #{url} and method = #{method}")
	public int apiClientAuthDelete(ApiVO apiVO);
	
	/**
	 * client API 업데이트할 정보 조회
	 * @param apiClientAuthVO
	 * @return
	 */
	@Select("select a.apiTypeCode, a.url, a.method, ac.client_id \r\n" + 
			"from api a, api_client_auth ac \r\n" + 
			"where ac.client_id = 'my_client_id' \r\n" + 
			"and a.apiTypeCode = ac.apiTypeCode \r\n" + 
			"and a.url = ac.url \r\n" + 
			"and a.method = ac.method \r\n" + 
			"and a.scope = 'public' \r\n" + 
			"and ac.scope = 'private'")
	public List<ApiClientAuthVO> apiClientAuthUpdateList(ApiClientAuthVO apiClientAuthVO); 
	
	/**
	 * client API 정보 업데이트
	 * @param apiClientAuthVO
	 * @return
	 */
	@Update("update api_client_auth \r\n" + 
			"set scope = 'public' \r\n" + 
			"where client_id = #{client_id} \r\n" + 
			"and apiTypeCode = #{apiTypeCode} \r\n" + 
			"and url = #{url} \r\n" + 
			"and method = #{method}")
	public int apiClientAuthUpdate(ApiClientAuthVO apiClientAuthVO);
	
	/**
	 * client API 정보 조회
	 * @param apiClientAuthVO
	 * @return
	 */
	@Select("select * from api_client_auth where client_id = #{client_id} and apiTypeCode = #{apiTypeCode}")
	public List<ApiClientAuthVO> apiClientAuthList(ApiClientAuthVO apiClientAuthVO);
	
	/**
	 * client API 정보 리스트 조회
	 * @param apiClientAuthVO
	 * @return
	 */
	@Select("select aca.url, aca.method, aca.scope, a.notes from api_client_auth aca, api a\r\n" + 
			"where aca.client_id = #{client_id}\r\n" + 
			"and aca.apiTypeCode = a.apiTypeCode\r\n" + 
			"and aca.url = a.url\r\n" + 
			"and aca.method = a.method")
	public List<ApiVO> clientApiList(ApiClientAuthVO apiClientAuthVO);
	
}
