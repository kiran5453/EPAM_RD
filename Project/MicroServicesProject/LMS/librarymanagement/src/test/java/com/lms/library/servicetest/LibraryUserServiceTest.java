package com.lms.library.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.library.clients.BookProxy;
import com.lms.library.clients.UserProxy;
import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.LibraryRecord;
import com.lms.library.datobjects.User;
import com.lms.library.dto.UserDto;
import com.lms.library.repository.LibraryRepository;
import com.lms.library.service.LibraryBookService;
import com.lms.library.service.LibraryUserService;

@SpringBootTest
class LibraryUserServiceTest {

	
	
	@MockBean
	UserProxy userProxy;
	
	@MockBean
	BookProxy bookProxy;
	
	@MockBean
	LibraryRepository libraryRepo;
	
	@Autowired
	LibraryUserService userService;
	
	@Autowired
	LibraryBookService bookService;
	
	UserDto userDto;
	User user;
	LibraryRecord libraryRecord;
	Book book;
	
	@BeforeEach
	void setup() {
		userDto =  new UserDto();
		userDto.setUserName("kiran5453");
		userDto.setEmail("saikiran5453@gmail.com");
		userDto.setName("kiran");
		
		user =  new User();
		user.setUserName("kiran5453");
		user.setEmail("saikiran5453@gmail.com");
		user.setName("kiran");
		
		libraryRecord = new LibraryRecord();
		libraryRecord.setId(1);
		libraryRecord.setBookId(101);
		libraryRecord.setUserName("kiran5453");
		
		book = new Book();
		book.setBookId(101);
		book.setBookName("book1");
		book.setBookPublisher("A1 publishers");
		book.setBookAuthor("chethan");
	}
	
	@Test
	void addUserTest() {
		when(userProxy.addUser(userDto))
		.thenReturn(new ResponseEntity<>("User with Username:" + user.getUserName() + " added succesfully",HttpStatus.OK));
	assertEquals("User with Username:" + user.getUserName() + " added succesfully",userService.addUser(userDto).getBody());
	}
	
	@Test
	void updateUserTest() {
		when(userProxy.updateUser(userDto.getUserName(),userDto))
		.thenReturn(new ResponseEntity<>("User with Username:" + user.getUserName() + " updated succesfully",HttpStatus.OK));
	assertEquals("User with Username:" + user.getUserName() + " updated succesfully",userService.updateUser(userDto.getUserName(),userDto).getBody());
	}

	@Test
	void deleteUserWithGivenUserNameTest() {
		List<LibraryRecord> record = new ArrayList<>();
		record.add(libraryRecord);
		when(libraryRepo.findByUserName("kiran5453")).thenReturn(record);
		doNothing().when(libraryRepo).deleteAll(record);
		when(userProxy.deleteUserWithGivenUserName("kiran5453"))
			.thenReturn(new ResponseEntity<>("User with Username: " + user.getUserName() + " deleted succesfully",HttpStatus.OK));
		assertEquals("User with Username: " + user.getUserName() + " deleted succesfully",userService.deleteUserWithGivenUserName("kiran5453").getBody());
	}
	
	@Test
	void getUserWithGivenUserNameExistRecordTest() {
		List<LibraryRecord> record = new ArrayList<>();
		record.add(libraryRecord);
		when(userProxy.getUserWithGivenUserName("kiran5453")).thenReturn(new ResponseEntity<>(user,HttpStatus.OK));
		when(libraryRepo.findByUserName("kiran5453")).thenReturn(record);
		when(bookProxy.getBookWithGivenId(101)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		assertEquals(book,userService.getUserWithGivenUserName("kiran5453").getBooks().get(0));
	}
	
	@Test
	void getUserWithGivenUserNameDoesNotExistRecordTest() {
		when(userProxy.getUserWithGivenUserName("kiran5453")).thenReturn(new ResponseEntity<>(user,HttpStatus.OK));
		when(libraryRepo.findByUserName("kiran5453")).thenReturn(null);
		assertEquals(true,userService.getUserWithGivenUserName("kiran5453").getBooks().isEmpty());
	}
	
	@Test
	void getUsersExistRecordText() {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userProxy.getUsers()).thenReturn(new ResponseEntity<>(userList,HttpStatus.OK));
		List<LibraryRecord> record = new ArrayList<>();
		record.add(libraryRecord);
		when(userProxy.getUserWithGivenUserName("kiran5453")).thenReturn(new ResponseEntity<>(user,HttpStatus.OK));
		when(libraryRepo.findByUserName("kiran5453")).thenReturn(record);
		when(bookProxy.getBookWithGivenId(101)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		assertEquals(userList,userService.getUsers());
		assertEquals(user,userService.getUsers().get(0));
		assertEquals(book,userService.getUsers().get(0).getBooks().get(0));
	}
	
	@Test
	void getUsersDoesNotExistRecordText() {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userProxy.getUsers()).thenReturn(new ResponseEntity<>(userList,HttpStatus.OK));
		when(userProxy.getUserWithGivenUserName("kiran5453")).thenReturn(new ResponseEntity<>(user,HttpStatus.OK));
		when(libraryRepo.findByUserName("kiran5453")).thenReturn(null);
		assertEquals(userList,userService.getUsers());
		assertEquals(user,userService.getUsers().get(0));
		assertEquals(true,userService.getUsers().get(0).getBooks().isEmpty());
	}
}
