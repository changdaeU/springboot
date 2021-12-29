package com.ezen.spm01.dao;

import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.AddressVO;
import com.ezen.spm01.dto.MemberVO;

@Mapper
public interface IMemberDao {

	MemberVO getMember(String id);

	ArrayList<AddressVO> selectAddressByDong(String dong);

	void insertMember(@Valid MemberVO membervo);

	void updateMember(@Valid MemberVO membervo);

}
