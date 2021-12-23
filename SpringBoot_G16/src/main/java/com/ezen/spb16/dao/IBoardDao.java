package com.ezen.spb16.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spb16.dto.BoardVO;
import com.ezen.spb16.dto.Paging;

@Mapper
public interface IBoardDao {

	List<BoardVO> selectBoardAll(Paging paging);

	int getAllCount();

	int getCount(int num);
	
}
