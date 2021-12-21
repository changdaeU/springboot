package com.ezen.spg10.dao;

import java.util.List;

import com.ezen.spg10.BbsDto;

public interface IBbsDao {
		public List<BbsDto> list();
		public int write(BbsDto bdto);
		public int update(BbsDto bdto);
		public int delete(String id);
		public BbsDto view(String id);
}
