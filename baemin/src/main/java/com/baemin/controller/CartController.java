package com.baemin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baemin.dto.Cart;
import com.baemin.dto.CartList;
import com.baemin.util.FoodPriceCalc;

@Controller
public class CartController {

	@ResponseBody
	@PostMapping("/addCart")
	public CartList addCart(Cart cart, long storeId, String storeName, int deliveryTip, HttpSession session) {
		CartList cartList = (CartList) session.getAttribute("cartList");
		int totalPrice = FoodPriceCalc.foodPriceCalc(cart);
		System.out.println("카트 가격 계산 : "+totalPrice);
		if(cartList == null) {
			List<Cart> newCart = new ArrayList<Cart>();
			cart.setTotalPrice(totalPrice);
			newCart.add(cart);
			cartList = new CartList(storeId, storeName, totalPrice, deliveryTip, newCart);
		}else {
			List<Cart> prevCart = cartList.getCart();
			int prevCartTotal = cartList.getCartTotal();
			cartList.setCartTotal(prevCartTotal + totalPrice);
			
			if(prevCart.contains(cart)) {
				int cartIndex = prevCart.indexOf(cart);
				int amount = cart.getAmount();
				
				Cart newCart = prevCart.get(cartIndex);
				int newAmount = newCart.getAmount() + amount;
				int newTotal = newCart.getTotalPrice() + totalPrice;
				
				newCart.setAmount(newAmount);
				newCart.setTotalPrice(totalPrice);
				prevCart.set(cartIndex, newCart);
			}else {
				cart.setTotalPrice(totalPrice);
				prevCart.add(cart);
			}
		}
		session.setAttribute("cartList", cartList);
		System.out.println("cartList =" +cartList );
		
		return cartList;
	}
	
	@ResponseBody
	@GetMapping("/cartList")
	public CartList cartList(HttpSession session) {
		CartList cartList = (CartList) session.getAttribute("cartList");
		if(cartList != null) {
			return cartList;
		}
		return null;
	}
	
	//장바구니 전체 삭제
	@ResponseBody
	@DeleteMapping("/cartAll")
	public void deleteAllCart(HttpSession session) {
		session.removeAttribute("cartList");
	}
	
	//장바구니 속 1개 삭제
	@ResponseBody
	@DeleteMapping("/cartOne")
	public CartList deleteOneCart(HttpSession session, int index) {
		CartList cartList = (CartList) session.getAttribute("cartList");
		if(cartList == null) {
			return null;
		}
		int cartTotal = cartList.getCartTotal();
		List<Cart> cart = cartList.getCart();
		int removeCartPrice = cart.get(index).getTotalPrice();
		cart.remove(index);
		if(cart.size()==0) {
			session.removeAttribute("cartList");
			return null;
		}
		cartTotal -= removeCartPrice;
		cartList.setCartTotal(cartTotal);
		return cartList;
	}
	
	//추가수량 변경하기
	@ResponseBody
	@PatchMapping("/cartAmount")
	public CartList cartAmount(int cartNum, String clickBtn, HttpSession session) {
		CartList cartList = (CartList) session.getAttribute("cartList");
		List<Cart> cart = cartList.getCart();
		
		Cart prevCart = cart.get(cartNum);
		
		int amount = prevCart.getAmount();
		int foodPrice = prevCart.getTotalPrice() / amount;
		int total = cartList.getCartTotal() - prevCart.getTotalPrice();
		
		if(clickBtn.equals("plus")) {
			amount++;
			foodPrice = foodPrice * amount;
			
			prevCart.setAmount(amount);
			prevCart.setTotalPrice(foodPrice);
		}else {
			if(amount <= 1) {
				return cartList;
			}
			amount--;
			foodPrice = foodPrice + amount;
			
			prevCart.setAmount(amount);
			prevCart.setTotalPrice(foodPrice);
		}
		cartList.setCartTotal(total + foodPrice);
		cart.set(cartNum, prevCart);
		return cartList;
	}
	
}
