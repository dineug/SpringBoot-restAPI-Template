package com.handcoding.restapi.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * 공통 mapper
 * @author 이승환
 * @version 2018.01.13 v1.0
 */
public interface CommonMapper {
	
	/**
	 * 페이징 총 개수 조회
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT FOUND_ROWS()")
	public int pagingTotal() throws Exception;
	
}
