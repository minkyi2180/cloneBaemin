package com.baemin.util;

import java.util.Calendar;

//주문번호 생성 - 현재날짜 + 랜덤 10자리 숫자
//주문번호 미리 만드는 이유 결제 api사용 위해서
public class CreateOrderNum {
	public static String createOrderNum() {
		Calendar cal = Calendar.getInstance();
		
		int y= cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		
		StringBuilder sb = new StringBuilder();
		sb.append(y).append(m).append(d);
		//append() = 선택된 요소의 마지막에 새로운 요소,콘텐츠 추가한다
		//stringbuilder > 문자결합 , 새로운 오브젝트 작성않고 문자열 추가
		
		for(int i=0;i<10;i++) {
			int random = (int)(Math.random() * 10);
			sb.append(random);
		}
		return sb.toString();
	}

}
