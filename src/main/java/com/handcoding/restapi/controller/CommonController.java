package com.handcoding.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 페이지 controller
 * @author 이승환
 * @version 2018.01.13 v1.0
 */
@Controller
public class CommonController {
	
	@GetMapping("/")
	public String swagger() throws Exception {
		return "swagger";
	}
	
}