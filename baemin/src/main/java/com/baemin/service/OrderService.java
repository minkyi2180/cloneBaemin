package com.baemin.service;

import javax.servlet.http.HttpSession;

import com.baemin.dto.CartList;
import com.baemin.dto.OrderInfo;
import com.baemin.login.LoginService;

public interface OrderService {
	//	장바구니에 담긴 금액과 db의 금액이 같은지 확인
	long orderPriceCheck(CartList cartList);

	public String order(CartList cart, OrderInfo info, LoginService user, HttpSession session);

	
}
