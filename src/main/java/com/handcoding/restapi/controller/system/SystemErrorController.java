package com.handcoding.restapi.controller.system;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handcoding.restapi.component.CommonComponent;
import com.handcoding.restapi.domain.ResponseVO;

import io.swagger.annotations.ApiOperation;

/**
 * 시스템 error
 * @author 이승환
 * @version 2018.01.28 v1.0
 */
@RestController
@RequestMapping("/error")
public class SystemErrorController {
	
	@Autowired
	private CommonComponent common;
	
	/**
	 * apiKey 에러처리
	 * @param locale
	 * @return
	 */
	@ApiOperation(value="", notes = "apiKey 에러처리")
	@GetMapping("/apikey")
	public ResponseEntity<ResponseVO<Object>> apikey(Locale locale) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		String message = null;
		message = common.getMsg().getMessage("no_key", new String[] {"API"}, locale);
		responseVO.setCode(404);
		responseVO.setCheck(false);
		responseVO.setMessage(message);
		return new ResponseEntity<ResponseVO<Object>>(responseVO, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * token 에러처리
	 * @param locale
	 * @return
	 */
	@ApiOperation(value="", notes = "token 에러처리")
	@GetMapping("/token")
	public ResponseEntity<ResponseVO<Object>> token(Locale locale) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		String message = null;
		message = common.getMsg().getMessage("no_key", new String[] {"token"}, locale);
		responseVO.setCode(404);
		responseVO.setCheck(false);
		responseVO.setMessage(message);
		return new ResponseEntity<ResponseVO<Object>>(responseVO, HttpStatus.NOT_FOUND);
	}
	
}
