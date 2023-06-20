package com.baemin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.baemin.login.LoginDetailService;
import com.baemin.login.LoginFail;
import com.baemin.login.LoginSuccess;
import com.baemin.login.OauthUserService;

@EnableWebSecurity
@Configuration
public class SecurityConfig<loginFail, loginSuccess> extends WebSecurityConfigurerAdapter{
	//로그인 성공, 실패시 
	@Autowired
	private LoginFail loginFail;
	
	@Autowired
	private LoginSuccess loginSuccess;
	
	//자동로그인
	@Autowired
	private LoginDetailService loginDetailService;
	
	@Autowired
	private OauthUserService oauthUserService;
	
	
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
	 
	    http.authorizeRequests()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/user/**").hasAnyRole("ADMIN, USER")
	        .anyRequest().permitAll()
	    .and()
	        .formLogin()
	        .loginPage("/") // 인증 필요한 페이지 접근시 이동페이지
	        .loginProcessingUrl("/login")
	        .successHandler(loginSuccess)
	        .failureHandler(loginFail)
	    .and()
	        .logout()
	        .logoutSuccessUrl("/myPage")
	    .and()
	    	.rememberMe()
	    	.key("rememberKey")
	    	.rememberMeCookieName("rememberMeCookieName")
	    	.rememberMeParameter("remember-me")
	    	.tokenValiditySeconds(60 * 60 * 24 *7)
	    	.userDetailsService(loginDetailService)
	    .and()
	    	.oauth2Login()
	    	.loginPage("/")
	    	.userInfoEndpoint()
	    	.userService(oauthUserService)
	    	;
	    //key("rememberKey") 쿠키값을 암호화할때 사용하는 키
	    //rememberMeParameter 로그인페이지 체크박스 name과 일치해야함
	    //rememberMeCookieName 저장할 쿠키이름 기본값 remember-me	
	    //tokenValiditySecond 쿠키 유지 시간  
	 
	}
	

}
