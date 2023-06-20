package com.baemin.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baemin.dto.Food;
import com.baemin.dto.Store;
import com.baemin.login.LoginService;

public interface AdminDAO {
	int pointUpdate(long userId, String info, int point);

	List<Long> getMyStoreId(long userId);

	void storeInfoUpdate(Store store);

	long addMenu(Food food);

	void addMenuOption(List<Map<String, Object>> optionList);
}
