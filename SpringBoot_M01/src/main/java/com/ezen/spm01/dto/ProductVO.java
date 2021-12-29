package com.ezen.spm01.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProductVO {
	
	private Integer pseq;
	private String name;
	private String kind;
	private Integer price1;
	private Integer price2;
	private Integer price3;
	private String content;
	private String image;
	private String useyn;
	private String bestyn;
	private Timestamp indate;
}
