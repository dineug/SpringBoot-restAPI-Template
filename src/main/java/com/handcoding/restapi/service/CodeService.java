package com.handcoding.restapi.service;

import java.util.List;

import com.handcoding.restapi.domain.CodeVO;
import com.handcoding.restapi.domain.SearchVO;

/**
 * 코드 서비스
 * @author 이승환
 * @version 2018.01.13 v1.0
 */
public interface CodeService {
	
	/**
	 * 코드조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<CodeVO> codeList(SearchVO searchVO) throws Exception;
	
	/**
	 * 코드생성
	 * @param codeVO
	 * @throws Exception
	 */
	public void codeInsert(CodeVO codeVO) throws Exception;
	
	/**
	 * 코드수정
	 * @param codeVO
	 * @return 성공여부
	 * @throws Exception
	 */
	public boolean codeUpdate(CodeVO codeVO) throws Exception;
	
	/**
	 * 코드삭제
	 * @param codeVO
	 * @return 성공여부
	 * @throws Exception
	 */
	public boolean codeDelete(CodeVO codeVO) throws Exception;
}
