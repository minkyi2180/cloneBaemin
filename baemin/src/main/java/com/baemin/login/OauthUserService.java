package com.baemin.login;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.baemin.dao.UserDAO;
import com.baemin.dto.Join;
import com.baemin.dto.User;

@Service
public class OauthUserService extends DefaultOAuth2UserService {
	@Autowired
	private SqlSession sql;
	
	@Autowired
	private BCryptPasswordEncoder encodePwd;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private OAuthUserInfo oauthUserInfo;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(userRequest);
		String provider = userRequest.getClientRegistration().getRegistrationId();
		String username = provider + "_" + oauth2User.getAttribute("sub"); 
		
		User user = sql.selectOne("user.login", username);
		
		if(user == null) {
			//첫 로그인시 유저정보 생성
			Join join = oauthUserInfo.createUser(provider, username, oauth2User);
			
			userDAO.join(join);
			
			user = sql.selectOne("user.login", username);
			
		}
 		
		LoginService loginService = new LoginService();
		loginService.setUser(user);
		
		return loginService;
	}
	
	
	

}
