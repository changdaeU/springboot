package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.ProductVO;

@Mapper
public interface IProductDao {

	ArrayList<ProductVO> getNewList();

	ArrayList<ProductVO> getBestList();

	ArrayList<ProductVO> getKindList(String kind);

	ProductVO getProduct(int pseq);
	
}
