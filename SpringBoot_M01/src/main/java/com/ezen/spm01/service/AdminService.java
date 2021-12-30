package com.ezen.spm01.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IAdminDao;
import com.ezen.spm01.dto.AdminVO;
import com.ezen.spm01.dto.Paging;
import com.ezen.spm01.dto.ProductVO;

@Service
public class AdminService {
	@Autowired
	IAdminDao adao;

	public int workerCheck(String workId, String workPwd) {
		String pwd = adao.workerCheck(workId, workPwd);	
		int result = 0;
		if(workPwd==null) result = -1; // 검색된 아이디가 없을때
		else if(workPwd.equals(pwd)) result = 1;
		else if(!workPwd.equals(pwd)) result = 0;
		return  result;
	}

	public ArrayList<ProductVO> listProduct(Paging paging, String key) {
		return adao.listProduct(paging, key);
	}

	public int getAllCount(String tableName, String fieldName, String key) {
		return adao.getAllCount(tableName, fieldName, key);
	}

	public void insertProduct(ProductVO pvo) {
		adao.insertProduct(pvo);
		
	}

	public void updateProduct(ProductVO pvo) {
		adao.updateProduct(pvo);
	}
}
