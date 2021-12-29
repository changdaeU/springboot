package com.ezen.spm01.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QnaVO {
	private Integer qseq;
	private String subject;
	private String content;
	private String reply;
	private String id;
	private String rep;
	private Timestamp indate;
}
