package com.handcoding.restapi.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.domain.CodeVO;
import com.handcoding.restapi.domain.PagingVO;
import com.handcoding.restapi.domain.ResponseVO;
import com.handcoding.restapi.domain.SearchVO;
import com.handcoding.restapi.service.CodeService;

import io.swagger.annotations.ApiOperation;

/**
 * 코드 API
 * @author 이승환
 * @version 2018.01.13 v1.0
 */
@RestController
@RequestMapping("/admin/codes")
public class CodeController {
	
	@Autowired
	private CodeService codeService;
	
	/**
	 * 코드조회
	 * @param p 페이징
	 * @param s 검색
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "코드조회")
	@GetMapping
	public ResponseVO<List<CodeVO>> codeList(PagingVO p, SearchVO s) throws Exception {
		ResponseVO<List<CodeVO>> responseVO = new ResponseVO<>();
		List<CodeVO> codeList = codeService.codeList(p, s);
		responseVO.setResponse(codeList);
		if(codeList.size() == 0) {
			responseVO.setCheck(false);
		}
		return responseVO;
	}
	
	/**
	 * 코드생성
	 * @param codeVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "코드생성")
	@PostMapping
	public ResponseVO<Object> codeInsert(@RequestBody CodeVO codeVO) throws Exception {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		codeService.codeInsert(codeVO);
		return responseVO;
	}
	
	/**
	 * 코드수정
	 * @param codeVO
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "코드수정")
	@PutMapping("/{code}")
	public ResponseVO<Object> codeUpdate(@RequestBody CodeVO codeVO, @PathVariable String code) throws Exception {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		codeVO.setCode(code);
		boolean check = codeService.codeUpdate(codeVO);
		responseVO.setCheck(check);
		return responseVO;
	}
	
	/**
	 * 코드삭제
	 * @param codeVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "코드삭제")
	@DeleteMapping("/{code}")
	public ResponseVO<Object> codeDelete(CodeVO codeVO) throws Exception {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		boolean check = codeService.codeDelete(codeVO);
		responseVO.setCheck(check);
		return responseVO;
	}
	
}
