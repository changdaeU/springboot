package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.QnaVO;

@Mapper
public interface IQnaDao {

	ArrayList<QnaVO> listQna(String id);

	void insertQna(QnaVO qvo);

	QnaVO getQna(int qseq);
	
}
