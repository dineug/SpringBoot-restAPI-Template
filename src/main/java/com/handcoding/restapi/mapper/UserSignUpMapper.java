package com.handcoding.restapi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.handcoding.restapi.domain.EmailConfirmVO;
import com.handcoding.restapi.domain.in.InUserSignUpVO;

/**
 * 회원가입 mapper
 * @author 이승환
 * @version 2018.01.23 v1.0
 */
public interface UserSignUpMapper {
	
	/**
	 * 회원가입
	 * @param inUserSignUpVO
	 * @return
	 */
	@Insert("insert user (id, pw, name, email, userTypeCode, statusCode)\r\n" + 
			"values(#{id}, SHA2(#{pw}, 256), #{name}, #{email}, #{userTypeCode}, #{statusCode})")
	public void userInsert(InUserSignUpVO inUserSignUpVO);
	
	/**
	 * 이메일인증 정보
	 * @param inUserSignUpVO
	 */
	@Insert("insert email_confirm (emailKey, id, userTypeCode, expiredDate)\r\n" + 
			"values(#{emailKey}, #{id}, #{userTypeCode}, date_add(now(), interval 1 day))")
	public void emailConfirmInsert(EmailConfirmVO emailConfirmVO);
	
	/**
	 * 이메일 인증
	 * @param emailConfirmVO
	 * @return
	 */
	@Update("update user\r\n" + 
			"set statusCode = 'USE'\r\n" + 
			"where id = (select id from email_confirm where emailKey = #{emailKey} and expiredDate > now())\r\n" + 
			"and userTypeCode = (select userTypeCode from email_confirm where emailKey = #{emailKey} and expiredDate > now())")
	public int emailConfirmUpdate(EmailConfirmVO emailConfirmVO);
	
	/**
	 * 이메일 key 존재여부 체크
	 * @param emailConfirmVO
	 * @return
	 */
	@Select("select count(*) from email_confirm where emailKey = #{emailKey}")
	public int emailConfirmKeyCheck(EmailConfirmVO emailConfirmVO);
	
}
