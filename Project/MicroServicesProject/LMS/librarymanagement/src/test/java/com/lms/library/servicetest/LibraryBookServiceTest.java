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
import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.LibraryRecord;
import com.lms.library.dto.BookDto;
import com.lms.library.repository.LibraryRepository;
import com.lms.library.service.LibraryBookService;

@SpringBootTest
class LibraryBookServiceTest {

	
	@MockBean
	BookProxy bookProxy;
	
	@MockBean
	LibraryRepository libraryRepo;
	
	@Autowired
	LibraryBookService bookService;
	
	LibraryRecord libraryRecord;
	Book book;
	BookDto bookDto;
	
	@BeforeEach
	void setup() {
		book = new Book();
		book.setBookId(101);
		book.setBookName("book1");
		book.setBookPublisher("A1 publishers");
		book.setBookAuthor("chethan");
		
		libraryRecord = new LibraryRecord();
		libraryRecord.setId(1);
		libraryRecord.setBookId(101);
		libraryRecord.setUserName("kiran5453");
		
		bookDto = new BookDto();
		bookDto.setBookId(101);
		bookDto.setBookName("book1");
		bookDto.setBookPublisher("A1 publishers");
		bookDto.setBookAuthor("chethan");
	}
	
	@Test
	void getBooksTest() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		when(bookProxy.getBooks()).thenReturn(new ResponseEntity<>(bookList,HttpStatus.OK));
		assertEquals(book,bookService.getBooks().getBody().get(0));
	}
	
	@Test
	void getBookWithGivenBookIdTest() {
		when(bookProxy.getBookWithGivenId(101)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		assertEquals(book,bookService.getBookWithGivenId(101).getBody());
	}

	@Test
	void deleteBookWithGivenBookIdTest() {
		List<LibraryRecord> record = new ArrayList<>();
		record.add(libraryRecord);
		when(libraryRepo.findByBookId(101)).thenReturn(record);
		doNothing().when(libraryRepo).deleteAll(record);
		when(bookProxy.deleteBookWithGivenId(101))
			.thenReturn(new ResponseEntity<>("Book with Id: " + book.getBookId() + " deleted succesfully",HttpStatus.OK));
		assertEquals("Book with Id: " + book.getBookId() + " deleted succesfully",bookService.deleteBookWithGivenId(101).getBody());
		
	}
	
	@Test
	void addBookTest() {
		when(bookProxy.addBook(bookDto))
			.thenReturn(new ResponseEntity<>("Book with Id: " + book.getBookId() + " added succesfully",HttpStatus.OK));
		assertEquals("Book with Id: " + book.getBookId() + " added succesfully",bookService.addBook(bookDto).getBody());
	}
	
	@Test
	void updateBookTest() {
		when(bookProxy.updateBook(bookDto.getBookId(),bookDto))
			.thenReturn(new ResponseEntity<>("Book with Id: " + book.getBookId() + " updated succesfully",HttpStatus.OK));
		assertEquals("Book with Id: " + book.getBookId() + " updated succesfully",bookService.updateBook(bookDto.getBookId(),bookDto).getBody());
	}
}
