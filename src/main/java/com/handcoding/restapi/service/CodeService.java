package com.handcoding.restapi.service;

import java.util.List;

import com.handcoding.restapi.domain.CodeVO;
import com.handcoding.restapi.domain.SearchVO;
import com.handcoding.restapi.domain.in.InCodeUpdateVO;

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
	 */
	public List<CodeVO> codeList(SearchVO searchVO);
	
	/**
	 * 코드생성
	 * @param codeVO
	 */
	public void codeInsert(CodeVO codeVO);
	
	/**
	 * 코드수정
	 * @param inCodeUpdateVO
	 * @return 성공여부
	 */
	public boolean codeUpdate(InCodeUpdateVO inCodeUpdateVO);
	
	/**
	 * 코드삭제
	 * @param codeVO
	 * @return 성공여부
	 */
	public boolean codeDelete(CodeVO codeVO);
}
