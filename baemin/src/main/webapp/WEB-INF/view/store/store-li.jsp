<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<li>
		 <a href="${store_admin }/detail/${storeList.id }"> 
		
		<div class="img_box">
			<a href="${store_admin }/detail/${storeList.id }"><img src="${storeList.storeImg }" alt="이미지"></a>
		</div>

		<div class="info_box">
		
			<h2><a href="${store_admin }/detail/${storeList.id }">${storeList.storeName }</a></h2>
			
			<a href="${store_admin }/detail/${storeList.id }">
				<span>
		
				</span>
				
			<span>
				
			</span>
			
			<span>
				<span>최소주문금액 <fm:formatNumber value="${storeList.minDelivery }" pattern="###,###" />원</span>
				<span>배달팁 <fm:formatNumber value="${storeList.deliveryTip }" pattern="###,###" />원</span>
			</span>
			<span>배달시간 ${storeList.deliveryTime }분</span>
			</a>
		</div>
		
	
</li>