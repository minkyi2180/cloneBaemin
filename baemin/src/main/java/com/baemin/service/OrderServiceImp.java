package com.baemin.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baemin.dao.OrderDAO;
import com.baemin.dao.AdminDAO;
import com.baemin.dto.Cart;
import com.baemin.dto.CartList;
import com.baemin.dto.OrderDetail;
import com.baemin.dto.OrderInfo;
import com.baemin.login.LoginService;
import com.baemin.util.UserInfoSessionUpdate;
import com.google.gson.Gson;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private AdminDAO adminDAO;

	@Transactional
	@Override
	public long orderPriceCheck(CartList cartList) {
		System.out.println("cartDetail = " +cartList);
		
		List<Cart> cart = cartList.getCart();
		List<Integer> foodPriceList = orderDAO.foodPriceList(cart);
		List<Integer> optionPriceList = orderDAO.optionPriceList(cart);
		int deliveryTip = orderDAO.getDeliveryTip(cartList.getStoreId());
		System.out.println("foodPriceList = " +foodPriceList);
		System.out.println("optionPriceList = " +optionPriceList);
		
		int sum = 0;
		for(int i=0; i<cart.size();i++) {
			int foodPrice = foodPriceList.get(i);
			int amount = cart.get(i).getAmount();
			int optionPrice = optionPriceList.get(i);
			
			sum += (foodPrice + optionPrice) * amount;
		}
		return sum + deliveryTip;
		
	}

	@Transactional
	@Override
	public String order(CartList cart, OrderInfo info, LoginService user, HttpSession session) {
		Gson gson = new Gson();
		
		System.out.println("info = " + info);
		
		int total =cart.getCartTotal();
		
		info.setStoreId(cart.getStoreId());
		info.setTotalPrice(total);
	
		long userId = 0;
		if(user != null) {
			userId = user.getUser().getId();
			info.setUserId(userId);
		}
		
		List<Cart> cartList = cart.getCart();
		
		OrderDetail[] detail = new OrderDetail[cartList.size()];
		
		for(int i=0;i<detail.length;i++) {
			String cartJSON = gson.toJson(cartList.get(i));
			detail[i] = new OrderDetail(info.getOrderNum(), cartJSON);
		}
		orderDAO.order(info);
		orderDAO.orderDetail(detail, userId);
		
		//회원 포인트 적립
		if (user != null) {
			String storeName = cart.getStoreName();
			int point = (int) (total * 0.01);
			int result = adminDAO.pointUpdate(userId, storeName, point);
			if (result == 1) {
				UserInfoSessionUpdate.sessionUpdate(point + "", "point", user, session);
			}

		}
		// 로그인 사용자가 포인트 사용했을때
		if (info.getUsedPoint() != 0) {
			String storeName = cart.getStoreName();
			int usedPoint = -info.getUsedPoint();
			int result = adminDAO.pointUpdate(userId, storeName, usedPoint);

			if (result == 1) {
				UserInfoSessionUpdate.sessionUpdate(usedPoint + "", "point", user, session);
			}
		}

		
		
		return null;
	}
}

