package com.baemin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baemin.dao.AdminDAO;
import com.baemin.dto.Food;
import com.baemin.dto.Store;

@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List<Long> getMyStoreId(long userId) {
		return adminDAO.getMyStoreId(userId);
	}

	@Override
	public void storeInfoUpdate(Store store) {
		adminDAO.storeInfoUpdate(store);
		
	}

	@Transactional
	@Override
	public void addMenu(Food food, String[] foodOption, Integer[] foodOptionPrice) {
		long foodId = adminDAO.addMenu(food);
		if(foodOption != null) {
			List<Map<String, Object>> optionList = new ArrayList<>();
			
			for(int i=0;i<foodOption.length;i++) {
				Map<String, Object> optionMap = new HashMap<>();
				optionMap.put("optionName", foodOption[i]);
				optionMap.put("optionPrice", foodOptionPrice[i]);
				optionMap.put("foodId", foodId);
				optionList.add(optionMap);
			}
			
			adminDAO.addMenuOption(optionList);
		}
		
	}

}
