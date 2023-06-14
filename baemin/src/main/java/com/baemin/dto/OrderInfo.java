package com.baemin.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderInfo {
	private String orderNum;
	private long storeId;
	private long userId;
	private Date orderDate;
	//java.util.date = 유닉스 시간/ 년월일시분초
	//java.sql.date = util.date 상속 / 년월일
	private String deliveryStatus;
	private int deliveryAddress1;
	private String deliveryAddress2;
	private String deliveryAddress3;
	private String payMethod;
	private int totalPrice;
	private int usedPoint;
	private String phone;
	private String request;
//	private String impUid = "";
	//아임포트 결제번호 추가
}
