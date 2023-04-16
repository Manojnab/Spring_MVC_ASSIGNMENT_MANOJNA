package com.spring.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.database.UsersDatabase;
import com.spring.mvc.dto.LoginDto;
import com.spring.mvc.entity.Book;

@Service
public class UsersService {
	@Autowired
	private UsersDatabase usersDatabase;
	public boolean validateUser(LoginDto loginDto,Map<String,String> map) throws Exception {
		
			if(this.usersDatabase.loginUser(loginDto,map)) {
				return true;
			}
			return false;
	}
	public boolean registerUser(LoginDto loginDto) throws Exception {
		
		if(this.usersDatabase.registerUser(loginDto)) {
			return true;
		}
		return false;
}
	public boolean likeBooks(Map<String,List<Book>> map) {
		return usersDatabase.likedBooks(map);
	}
	public boolean readLater(Map<String,List<Book>> map) {
		return usersDatabase.readLater(map);
	}

}
