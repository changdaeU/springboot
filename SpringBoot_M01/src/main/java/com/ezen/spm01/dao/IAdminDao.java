package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.AdminVO;
import com.ezen.spm01.dto.Paging;
import com.ezen.spm01.dto.ProductVO;

@Mapper
public interface IAdminDao {

	String workerCheck(String workId, String workPwd);

	ArrayList<ProductVO> listProduct(Paging paging, String key);

	int getAllCount(String tableName, String fieldName, String key);

	void insertProduct(ProductVO pvo);

	void updateProduct(ProductVO pvo);
	
}
