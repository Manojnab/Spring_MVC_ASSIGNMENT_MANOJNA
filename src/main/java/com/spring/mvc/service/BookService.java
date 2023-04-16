package com.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.database.BookDatabase;
import com.spring.mvc.database.UsersDatabase;
import com.spring.mvc.dto.LoginDto;
import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.UserBook;

@Service
public class BookService {
	@Autowired
	private BookDatabase bookDatabase;
	@Autowired
	private UsersDatabase usersDatabase;
	public List<Book> getBooks() throws Exception {
		
			return bookDatabase.getAllBooks();
	}
	
	public boolean userBookSave(UserBook userbook) {
		return usersDatabase.userBookSave(userbook);
		
	}

}
