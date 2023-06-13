package com.baemin.dto;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Join {
	@Pattern(regexp ="[A-Za-z0-9]{4,15}$", message = "아이디는 영어, 숫자 4 ~15자리로 입력 가능합니다")
	
	private String username;
	private String password;
	
	@Pattern(regexp = "^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$" , message = "올바른 이메일 형식이 아닙니다")
	private String email;
	
	//@Email사용해도 되지만 null허용하기에 pattern 사용
	
	@Pattern(regexp = "^[가-힣|a-z|A-Z|0-9|]+$", message = "닉네임은 한글, 영어, 숫자만 4 ~10자리로 입력 가능합니다")
	private String nickname;
	
	@Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "휴대폰번호를 확인해 주세요")
	private String phone;
	
	
	//regexp는 문자열에서 특정 내용을 검색, 대체, 발췌하는데 사용
	//일정한 패턴을 가진 문자열의 집합을 표현하기 위해 사용하는 형식 언어
	//pattern 유효성 검증하기!(문자열의 일정한 규칙)
}
