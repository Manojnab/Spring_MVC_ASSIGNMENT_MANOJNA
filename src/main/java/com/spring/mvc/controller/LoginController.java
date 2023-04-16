package com.spring.mvc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.dto.LoginDto;
import com.spring.mvc.entity.Book;
import com.spring.mvc.service.BookService;
import com.spring.mvc.service.UsersService;

@Controller
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private BookService bookService;
	public LoginController() {
		System.out.println("Login controller callled");
	}
	@GetMapping("/login")
	//@RequestMapping(path="/login",method=RequestMethod.GET)
	public String loginPage(HttpServletRequest httpServletRequest, Map<String, List<String>> map,
			Map<String,String> errorMap,
			@RequestParam(required = false) String error) {
		if(error!=null) {
			errorMap.put("error", error);
		}
		System.out.println("login called"+httpServletRequest.getMethod());
		List<String> roles=Arrays.asList("ADMIN","USER","DEVELOPER");
		map.put("roles",roles);
		return "login";
	}
	@GetMapping("/logout")
	//@RequestMapping(path="/login",method=RequestMethod.GET)
	public String logoutPage(HttpSession httpSession) {
		httpSession.removeAttribute("email");
		httpSession.invalidate();
		return "redirect:login";
	}
//	@PostMapping("/login")
//	//@RequestMapping(path="/login",method=RequestMethod.POST)
//	public String loginPostPage(@RequestParam String email, @RequestParam String password, HttpServletRequest httpServletRequest) {
//		System.out.println("login called"+httpServletRequest.getMethod());
//		List<String> roles=Arrays.asList("ADMIN","USER","DEVELOPER");
//		//map.put("roles",roles);
//		System.out.println("email "+email);
//		System.out.println("password "+password);
//		if(email.equals("sh@g.c")&&password.equals("sh")) {
//			//redirect dest get mapping
//			return "redirect:dashboard";
//		}
//		
//		
//		return "redirect:login";
//	}
	@PostMapping("/login")
	//@RequestMapping(path="/login",method=RequestMethod.POST)
	public String loginPostPage(LoginDto loginDto, HttpServletRequest httpServletRequest,
			HttpSession httpSession, Map<String,List<Book>> map,Map<String,String> username) throws Exception {
		List<Book> books=bookService.getBooks();
		map.put("books", books);
		System.out.println("login called   "+httpServletRequest.getMethod());
		//List<String> roles=Arrays.asList("ADMIN","USER","DEVELOPER");
		//map.put("roles",roles);
		System.out.println("email "+loginDto.getEmail());
		System.out.println("password "+loginDto.getPassword());
		try {
			if(this.usersService.validateUser(loginDto,username)) {
				//redirect dest get mapping
				httpSession.setAttribute("email", loginDto.getEmail());
				return "bookListLike";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "redirect:login?error=Invalid Credentials";
		}
		
		
		return "redirect:login";
	}

}
