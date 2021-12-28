package com.ezen.spb16.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyVO {
	private String userid;
	private String content;
	private int num;
	private int boardnum;
	private Timestamp writedate;
}
