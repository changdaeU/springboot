package com.ezen.spm01.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IQnaDao;
import com.ezen.spm01.dto.QnaVO;

@Service
public class QnaService {
	@Autowired
	IQnaDao qdao;

	public ArrayList<QnaVO> listQna(String id) {
		return qdao.listQna(id);
	}

	public void insertQna(QnaVO qvo) {
		qdao.insertQna(qvo);
		
	}

	public QnaVO getQna(int qseq) {
		return qdao.getQna(qseq);
	}
}
