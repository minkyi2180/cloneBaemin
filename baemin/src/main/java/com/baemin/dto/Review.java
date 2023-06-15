package com.baemin.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Review {
	private String orderNum;
	private long storeId;
	private String storeName;
	private String reviewContent;
	private String bossComment;
	private Date regiDate;
	private float score; //float는 7자리 double은 15~16자리 표현
	private String reviewImg;
	
	private long userId;
	private String username;
	private String nickname;

}
