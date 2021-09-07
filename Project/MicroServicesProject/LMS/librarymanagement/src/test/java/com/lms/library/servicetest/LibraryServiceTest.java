package com.lms.library.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.library.clients.BookProxy;
import com.lms.library.clients.UserProxy;
import com.lms.library.customexceptions.RecordNotFoundException;
import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.LibraryRecord;
import com.lms.library.datobjects.User;
import com.lms.library.dto.UserDto;
import com.lms.library.repository.LibraryRepository;
import com.lms.library.service.LibraryBookService;
import com.lms.library.service.LibraryService;
import com.lms.library.service.LibraryUserService;

@SpringBootTest
class LibraryServiceTest {

	
	@MockBean
	UserProxy userProxy;
	
	@MockBean
	BookProxy bookProxy;
	
	@MockBean
	LibraryRepository libraryRepo;
	
	@MockBean
	LibraryUserService userService;
	
	@MockBean
	LibraryBookService bookService;
	
	@Autowired
	LibraryService libraryService;
	
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
	void issueBookTestWithoutRecord() {
		when(userService.getUserWithGivenUserName("kiran5453")).thenReturn(user);
		when(bookService.getBookWithGivenId(101)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		when(libraryRepo.existsByUserNameAndBookId("kiran5453", 101)).thenReturn(false);
		when(libraryRepo.save(libraryRecord)).thenReturn(libraryRecord);
		assertEquals("Book with Id: "+book.getBookId()+" issued to User with Username: "+user.getUserName(),libraryService.issueBook("kiran5453", 101));
	}

	@Test
	void issueBookTestWithRecord() {
		when(userService.getUserWithGivenUserName("kiran5453")).thenReturn(user);
		when(bookService.getBookWithGivenId(101)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		when(libraryRepo.existsByUserNameAndBookId("kiran5453", 101)).thenReturn(true);
		assertEquals("Book with Id: "+book.getBookId()+" already exists for User with Username: "+user.getUserName(),libraryService.issueBook("kiran5453", 101));
	}
	
	@Test
	void releaseBookTestWithBook() throws RecordNotFoundException {
		when(libraryRepo.findByUserNameAndBookId("kiran5453", 101)).thenReturn(libraryRecord);
		doNothing().when(libraryRepo).delete(libraryRecord);
		assertEquals("Book with Id: "+book.getBookId()+" relased from User with Username: "+user.getUserName(),libraryService.releaseBook("kiran5453", 101));
	}
	
	@Test
	void releaseBookTestWithoutBook() throws RecordNotFoundException {
		when(libraryRepo.findByUserNameAndBookId("kiran5453", 102)).thenReturn(null);
		doNothing().when(libraryRepo).delete(libraryRecord);
		assertThrows(RecordNotFoundException.class,()->{
			libraryService.releaseBook("kiran5453", 102);
		});
	}
}
