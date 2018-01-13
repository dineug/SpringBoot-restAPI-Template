package com.handcoding.restapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handcoding.restapi.domain.CodeVO;
import com.handcoding.restapi.domain.PagingVO;
import com.handcoding.restapi.domain.SearchVO;
import com.handcoding.restapi.mapper.CodeMapper;
import com.handcoding.restapi.mapper.CommonMapper;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CodeServiceImpl implements CodeService {
	
	@Autowired
	private CodeMapper codeMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	// 코드 조회
	@Override
	public List<CodeVO> codeList(PagingVO p, SearchVO s) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("p", p);
		map.put("s", s);
		List<CodeVO> list = codeMapper.codeList(map);
		int total = commonMapper.pagingTotal();
		for (CodeVO codeVO : list) {
			codeVO.setTotal(total);
		}
		return list;
	}
	
	// 코드생성
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void codeInsert(CodeVO codeVO) throws Exception {
		codeMapper.codeInsert(codeVO);
	}
	
	// 코드수정
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public boolean codeUpdate(CodeVO codeVO) throws Exception {
		int check = codeMapper.codeUpdate(codeVO);
		return check > 0;
	}
	
	// 코드삭제
	@Override
	public boolean codeDelete(CodeVO codeVO) throws Exception {
		int check = codeMapper.codeDelete(codeVO);
		return check > 0;
	}
	
}
