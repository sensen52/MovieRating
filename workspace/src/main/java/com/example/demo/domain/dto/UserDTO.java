package com.example.demo.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
	
	//회원id
	private String userId;
	//회원이름
	private String userName;
	//회원 pw
	private String userPw;
	//회원 email
	private String userEmail;
	//회원 생년월일
	private String userBirthday;
	//관리자, 일반회원 구분(관리자 : 1, 일반회원 : 0)
	private int userRole;

}
