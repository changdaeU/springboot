package com.ezen.spm01.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberVO {
	
	@NotNull @NotEmpty(message="id를 입력하세요")
	private String id;
	@NotNull  @NotEmpty(message="비밀번호를 입력하세요")
	private String pwd;
	@NotNull  @NotEmpty(message="이름을 입력하세요")
	private String name;
	@NotNull  @NotEmpty(message="이메일을 입력하세요")
	private String email;
	private String zip_num;
	private String address;
	private String phone;
	private String useyn;
	private Timestamp indate;
}
