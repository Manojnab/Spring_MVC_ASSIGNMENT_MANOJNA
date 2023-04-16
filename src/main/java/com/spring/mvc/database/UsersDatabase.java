package com.spring.mvc.database;

import java.util.List;
import java.util.Map;

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
public class UsersDatabase {
	
	@Autowired
	private SessionFactory factory;
	
	public List<User> getAllUsers(){
		Session session = factory.openSession();
		List<User> users=session.createQuery("from user",User.class).getResultList();
		session.close();
		return users;
	}
	User user;
	public boolean loginUser(LoginDto loginDto,Map<String,String> map) throws Exception {
		System.out.println(loginDto);
		Session session = factory.openSession();
		user=session.get(User.class,loginDto.getEmail());
		map.put("username", user.getUserName());
		System.out.println("from userdatabase"+user);
		if(user!=null) {
			if(loginDto.getPassword().equals(user.getPassword())) {
				session.close();
				return true;
			}
		}
		session.close();
		throw new Exception("Invalid creds");
		
	}
	public boolean registerUser(LoginDto loginDto) throws Exception {
		System.out.println(loginDto);
		Session session = factory.openSession();
		System.out.println("about to start the tansaction");
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setUserName(loginDto.getUsername());
		user.setEmail(loginDto.getEmail());
		user.setPassword(loginDto.getPassword());
		session.save(user);
		tx.commit();
		session.close();
		return true;
		
		
	}
	public boolean userBookSave(UserBook userbook) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		userbook.setEmail(user.getEmail());
		session.merge(userbook);
		tx.commit();
		session.close();
		return true;
	}
	
	public boolean likedBooks(Map<String,List<Book>> map) {
		Session session = factory.openSession();
		List<Book> likedBooks = session.createQuery("select a from Book a where id in(select id from UserBook where email=?1 and isLiked=?2)", Book.class)
				.setParameter(1, user.getEmail())
				.setParameter(2, "like")
				.getResultList();
		//session.get(User.class,loginDto.getEmail());
		map.put("likedBooks", likedBooks);
		System.out.println("liked books are"+likedBooks);
		return true;
		}
	public boolean readLater(Map<String,List<Book>> map) {
		Session session = factory.openSession();
		List<Book> readLater = session.createQuery("select a from Book a where id in(select id from UserBook where email=?1 and isReadLater=?2)", Book.class)
				.setParameter(1, user.getEmail())
				.setParameter(2, "read later")
				.getResultList();
		//session.get(User.class,loginDto.getEmail());
		map.put("readLater", readLater);
		System.out.println("liked books are"+readLater);
		return true;
		}
//	public boolean addLikeReadLater(String like,String readLater,) {
//		
//		System.out.println();
//		
//	}

}
