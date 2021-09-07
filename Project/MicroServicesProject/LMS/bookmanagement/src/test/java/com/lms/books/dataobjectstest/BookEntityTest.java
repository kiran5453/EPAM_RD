package com.lms.books.dataobjectstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.books.dataobjects.Book;


@SpringBootTest
class BookEntityTest {

	Book book;
	
	@BeforeEach
	void setup() {
		book = new Book();
		book.setBookId(101);
		book.setBookName("book1");
		book.setBookPublisher("A1 publishers");
		book.setBookAuthor("chethan");
	}
	
	@Test
	void getBookIdTest() {
		assertEquals(101,book.getBookId());
	}
	
	@Test
	void getBookNameTest() {
		assertEquals("book1",book.getBookName());
	}
	
	@Test
	void getBookPublisherTest() {
		assertEquals("A1 publishers",book.getBookPublisher());
	}
	
	@Test
	void getBookAuthorTest() {
		assertEquals("chethan",book.getBookAuthor());
	}

}
