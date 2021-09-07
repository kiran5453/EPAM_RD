package com.lms.library.dataobjectstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.User;

@SpringBootTest
public class UserEntityTest {

	User user;
	
	@BeforeEach
	void setup() {
		user =  new User();
		user.setUserName("kiran5453");
		user.setEmail("saikiran5453@gmail.com");
		user.setName("kiran");
		user.setBooks(new ArrayList<Book>());
	}
	
	@Test
	void getUserNameTest() {
		assertEquals("kiran5453",user.getUserName());
	}
	
	@Test
	void getEmailTest() {
		assertEquals("saikiran5453@gmail.com",user.getEmail());
	}
	
	@Test
	void getNameTest() {
		assertEquals("kiran",user.getName());
	}
	
	@Test
	void getBooksTest() {
		assertEquals(true,user.getBooks().isEmpty());
	}
}
