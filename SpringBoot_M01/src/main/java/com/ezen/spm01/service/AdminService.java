package com.ezen.spm01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IAdminDao;

@Service
public class AdminService {
	@Autowired
	IAdminDao adao;
}
