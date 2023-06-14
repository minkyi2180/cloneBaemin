package com.baemin.dao;

import javax.servlet.http.HttpSession;

import com.baemin.login.LoginService;

public interface AdminDAO {
	int pointUpdate(long userId, String info, int point);
}
