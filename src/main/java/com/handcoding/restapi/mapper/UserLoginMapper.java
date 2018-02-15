package com.handcoding.restapi.mapper;

import org.apache.ibatis.annotations.Select;

import com.handcoding.restapi.domain.in.InUserLoginVO;
import com.handcoding.restapi.domain.out.OutUserLoginVO;

/**
 * 로그인 mapper
 * @author 이승환
 * @version 2018.01.30 v1.0
 */
public interface UserLoginMapper {
	
	/**
	 * 로그인
	 * @param inUserLoginVO
	 * @return
	 */
	@Select("select id, name, email, userTypeCode, statusCode, UNIX_TIMESTAMP(regDate) regDate from user\r\n" + 
			"where binary(id) = #{id} and pw = SHA2(#{pw}, 256) and statusCode = 'USE' and userTypeCode = #{userTypeCode}")
	public OutUserLoginVO login(InUserLoginVO inUserLoginVO);
	
}
