package com.spring.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.UserBook;
import com.spring.mvc.service.BookService;
import com.spring.mvc.service.UsersService;

@Controller
public class BookListController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UsersService usersService;
	@GetMapping("/bookList")
	public String bookList(Map<String,List<Book>> map) throws Exception {
		List<Book> books=bookService.getBooks();
		map.put("books", books);
		return "bookList";
	}
	
	@GetMapping("/like")
	public String like(@RequestParam String likeBook,@RequestParam String readLaterBook,@RequestParam String bookId) {
		System.out.println("checkbox clicked like method called "+likeBook+" read later "+readLaterBook+"username "+bookId  );
		UserBook userbook=new UserBook();
		String[] bookIdString=bookId.split(",");
		String[] likeBookString=likeBook.split(",");
		String[] readLaterBookString=readLaterBook.split(",");
		for(int i=0;i<bookIdString.length;i++) {
		userbook.setId(Integer.valueOf(bookIdString[i]));
		userbook.setIsLiked(likeBookString[i]);
		userbook.setIsReadLater(readLaterBookString[i]);
		bookService.userBookSave(userbook);
		}
		
		
		return "likedBooks";
	}
	@GetMapping("/bookListLike")
	public String bookListLike(Map<String,List<Book>> map) throws Exception {
		List<Book> books=bookService.getBooks();
		map.put("books", books);
		return "bookListLike";
	}
	@GetMapping("/likedBooks")
	public String likedBooks(Map<String,List<Book>> map) {
		usersService.likeBooks(map);
		return "likedBooks";
	}
	@GetMapping("/readLater")
	public String readLater(Map<String,List<Book>> map) {
		usersService.readLater(map);
		return "readLater";
	}
}

