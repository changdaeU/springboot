package com.ezen.spb16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spb16.dao.IBoardDao;
import com.ezen.spb16.dto.BoardVO;
import com.ezen.spb16.dto.Paging;

@Service
public class BoardService {
	@Autowired
	IBoardDao bdao;

	public List<BoardVO> selectBoardAll(Paging paging) {
		List<BoardVO> list =  bdao.selectBoardAll(paging);
		
		//10개의 게시물 리스트가 리턴되어 list에 저장됩니다.
		for(BoardVO bvo : list) {
			// 각 게시물번호를 이용하여 댓글 개수를 조회하는 메서드를 호출하여 개수를 얻습니다.
			int count = bdao.getCount(bvo.getNum());
			// 조회된 댓글 개수를 dto에 업데이트 
			bvo.setReplycnt(count);
		}
		
		return list;
	}

	public int getAllCount() {
		return bdao.getAllCount();
	}
}
