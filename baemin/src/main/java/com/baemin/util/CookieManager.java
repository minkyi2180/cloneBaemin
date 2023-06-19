package com.baemin.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CookieManager {
	public String findCookie(String cookieName) throws Exception{
		ServletRequestAttributes attr =
				(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		Cookie[] cookies = attr.getRequest().getCookies();
		for(int i =0; i<cookies.length;i++) {
			if(cookies[i].getName().equals(cookieName)) {
				return URLDecoder.decode(cookies[i].getValue(),"UTF-8");
			}
		}
		return null;
	}
	
	public void likes(long storeId)throws Exception{
		final String LIKES_LIST = "LIKES_LIST";
		String cookie = findCookie(LIKES_LIST);
		List<Long> list = new ArrayList<Long>();
		
		if(cookie==null) {
			list.add(storeId);
			addCookie(LIKES_LIST, list.toString());
			System.out.println("찜 목록= "+list);
			return;
		}
		StringTokenizer st = new StringTokenizer(cookie,",");
		while(st.hasMoreTokens()) {
			list.add(Long.parseLong(st.nextToken()));
		}
		if(list.contains(storeId)) {
			list.remove(storeId);
		}else {
			list.add(storeId);
		}
		if(list.size()==0) {
			addCookie(LIKES_LIST,"");
		}else {
			addCookie(LIKES_LIST,list.toString());
		}
		System.out.println("찜 목록 = "+list);
	}
	public void addCookie(String name, String value) throws Exception{
		ServletRequestAttributes attr =
				(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		Cookie cookie = new Cookie(name, URLEncoder.encode(value,"UTF-8"));
		cookie.setMaxAge(60*60*24*30);
		attr.getResponse().addCookie(cookie);
	}

}
