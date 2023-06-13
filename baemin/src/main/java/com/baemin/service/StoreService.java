package com.baemin.service;

import java.util.List;

import com.baemin.dto.FoodOption;
import com.baemin.dto.Store;
import com.baemin.dto.StoreDetail;

public interface StoreService {
	List<Store> storeList(int category, int address);
	StoreDetail storeDetail(long id);
	List<FoodOption> foodOption(int foodId);//해당메뉴 옵션 가져오기

}
