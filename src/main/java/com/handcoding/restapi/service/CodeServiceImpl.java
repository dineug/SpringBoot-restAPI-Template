package com.handcoding.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handcoding.restapi.component.MapperComponent;
import com.handcoding.restapi.domain.CodeVO;
import com.handcoding.restapi.domain.SearchVO;
import com.handcoding.restapi.domain.in.InCodeUpdateVO;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CodeServiceImpl implements CodeService {
	
	@Autowired
	private MapperComponent mapper;
	
	// 코드 조회
	@Override
	public List<CodeVO> codeList(SearchVO searchVO) throws Exception {
		List<CodeVO> codeList = mapper.getCodeMapper().codeList(searchVO);
		// 페이징 total값 세팅
		int total = mapper.getCommonMapper().pagingTotal();
		for (CodeVO codeVO : codeList) {
			codeVO.setTotal(total);
		}
		return codeList;
	}
	
	// 코드생성
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName="Exception")
	public void codeInsert(CodeVO codeVO) throws Exception {
		mapper.getCodeMapper().codeInsert(codeVO);
	}
	
	// 코드수정
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName="Exception")
	public boolean codeUpdate(InCodeUpdateVO inCodeUpdateVO) throws Exception {
		int checkNum = mapper.getCodeMapper().codeUpdate(inCodeUpdateVO);
		return checkNum > 0;
	}
	
	// 코드삭제
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName="Exception")
	public boolean codeDelete(CodeVO codeVO) throws Exception {
		int checkNum = mapper.getCodeMapper().codeDelete(codeVO);
		return checkNum > 0;
	}
	
}
