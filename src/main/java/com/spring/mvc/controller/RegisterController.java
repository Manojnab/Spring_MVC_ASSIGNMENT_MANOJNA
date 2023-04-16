package com.spring.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.mvc.dto.LoginDto;
import com.spring.mvc.entity.Book;
import com.spring.mvc.service.BookService;
import com.spring.mvc.service.UsersService;

@Controller
public class RegisterController {
	
	@Autowired
	private UsersService usersService;
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/register")
	public String loginPostPage(LoginDto loginDto, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws Exception {
		System.out.println("login called   "+httpServletRequest.getMethod());
		//List<String> roles=Arrays.asList("ADMIN","USER","DEVELOPER");
		//map.put("roles",roles);
		System.out.println("email "+loginDto.getEmail());
		System.out.println("password "+loginDto.getPassword());
		System.out.println("username "+loginDto.getUsername());

		try {
			if(this.usersService.registerUser(loginDto)) {
				//redirect dest get mapping
				//httpSession.setAttribute("email", loginDto.getEmail());
				return "redirect:login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "redirect:login?error=Invalid Credentials";
		}
		
		
		return "redirect:login";
	}

}
