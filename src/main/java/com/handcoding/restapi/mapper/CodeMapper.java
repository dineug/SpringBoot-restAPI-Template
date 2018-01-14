package com.handcoding.restapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.handcoding.restapi.domain.CodeVO;
import com.handcoding.restapi.domain.SearchVO;

/**
 * 코드 mapper
 * @author 이승환
 * @version 2018.01.13 v1.0
 */
public interface CodeMapper {
	
	/**
	 * 코드조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CodeVO> codeList(SearchVO searchVO) throws Exception;
	
	/**
	 * 코드생성
	 * @param codeVO
	 * @throws Exception
	 */
	@Insert("insert into code values(#{code}, #{parentCode}, #{descript})")
	public void codeInsert(CodeVO codeVO) throws Exception;
	
	/**
	 * 코드수정
	 * @param codeVO
	 * @return 업데이트 갯수
	 * @throws Exception
	 */
	public int codeUpdate(CodeVO codeVO) throws Exception;
	
	/**
	 * 코드삭제
	 * @param codeVO
	 * @return 삭제 갯수
	 * @throws Exception
	 */
	@Delete("delete from code where code = #{code} and parentCode = #{parentCode}")
	public int codeDelete(CodeVO codeVO) throws Exception;
	
}
