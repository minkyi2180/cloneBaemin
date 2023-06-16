package com.baemin.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Page {
	private int view = 10;//화면에 출력할 목록 수
	private int firstList; //페이지 첫번째 목록
	private int lastList; //페이지 마지막 목록
	
	private int pageCount = 5;
	private int firstPage;
	private int lastPage;
	private int prevPage;
	private int nextPage;
	private int nowPage;
	
	public Page() {
		this(1);
	}
	public Page(int movePage) {
		page(movePage, view);
	}
	public Page(int movePage, int view) {
		page(movePage, view);
	}
	
	public Page(Integer page) {
		int movePage = 1;
		if(page != null) {
			movePage = page;
		}
		page(movePage, view);
	}
	
	public void page(int movePage, int view) {
		this.firstList = (view * movePage) -view + 1;
		this.lastList = movePage * view;
		nowPage = movePage;
		firstPage = movePage - (movePage -1) % pageCount;
		lastPage = firstPage + pageCount - 1;
		prevPage = firstPage -1;
		nextPage = firstPage + pageCount;
	}
	
	private int totalPage; //총 페이지 수
	public void totalPage(int listCount) {
		if(listCount % view == 0) {
			totalPage = listCount / view;
		}else {
			totalPage = listCount / view + 1;
		}
	}

}
