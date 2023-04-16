package com.spring.mvc.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mvc.dto.LoginDto;
import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.User;
import com.spring.mvc.entity.UserBook;

@Repository
public class BookDatabase {
	
	@Autowired
	private SessionFactory factory;
	
	public List<Book> getAllBooks(){
		Session session = factory.openSession();
		List<Book> books=session.createQuery("from Book",Book.class).getResultList();
		System.out.println(books);
		session.close();
		return books;
	}
	
//	public boolean loginUser(LoginDto loginDto) throws Exception {
//		System.out.println(loginDto);
//		Session session = factory.openSession();
//		User user=session.get(User.class,loginDto.getEmail());
//		System.out.println(user);
//		if(user!=null) {
//			if(loginDto.getPassword().equals(user.getPassword())) {
//				session.close();
//				return true;
//			}
//		}
//		session.close();
//		throw new Exception("Invalid creds");
//		
//	}

}
