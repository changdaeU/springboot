package com.ezen.spb16.dao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spb16.dto.BoardVO;
import com.ezen.spb16.dto.Paging;
import com.ezen.spb16.dto.ReplyVO;

@Mapper
public interface IBoardDao {

	List<BoardVO> selectBoardAll(Paging paging);

	int getAllCount();

	int getCount(int num);

	void insertBoard(BoardVO bdto);

	BoardVO getBoard(int num);

	void plusReadCount(int num);

	ArrayList<ReplyVO> selectReply(int num);

	void addReply(ReplyVO rvo);

	void deleteReply(int num);

	void updateBoard(@Valid BoardVO boardvo);

	void boardDelete(int num);

	void replyDelete(int num);
	
}
