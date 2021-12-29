package com.ezen.spm01.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IMemberDao;
import com.ezen.spm01.dto.AddressVO;
import com.ezen.spm01.dto.MemberVO;

@Service
public class MemberService {
	@Autowired
	IMemberDao mdao;

	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}

	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		return mdao.selectAddressByDong(dong);
	}

	public void insertMember(@Valid MemberVO membervo) {
		mdao.insertMember(membervo);
		
	}

	public void updateMember(@Valid MemberVO membervo) {
		mdao.updateMember(membervo);
		
	}
}
