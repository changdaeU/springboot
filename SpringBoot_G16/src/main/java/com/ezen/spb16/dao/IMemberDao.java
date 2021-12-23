package com.ezen.spb16.dao;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spb16.dto.MemberVO;

@Mapper
public interface IMemberDao {

	MemberVO getMember(String id);

	void insertMember(MemberVO membervo);

	void updateMember(@Valid MemberVO membervo);
	
}
