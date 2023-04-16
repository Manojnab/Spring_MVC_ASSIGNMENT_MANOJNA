package com.spring.mvc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginDto {
	private String email;
	private String password;
	private String username;
	
	

}
