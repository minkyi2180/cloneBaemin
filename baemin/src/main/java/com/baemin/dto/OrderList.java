package com.baemin.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderList {
	private String orderNum; 
	private long storeId; 
	private long userId; 
	private Date orderDate; 
	private String deliveryStatus; 
	private int deliveryAddress1; 
	private String deliveryAddress2; 
	private String deliveryAddress3; 
	private String payMethod; 
	private int totalPrice;
	private int usedPoint;
	private String phone;
	private String request;
	
	private String foodInfo;
	
	private String storeName;
	private String storeImg;
	private String storeThumb;
	private int deliveryTip;
	
	private String reviewContent;
	private int score;
    private String reviewImg;
    private int listCount; // 목록 총 갯수
}
