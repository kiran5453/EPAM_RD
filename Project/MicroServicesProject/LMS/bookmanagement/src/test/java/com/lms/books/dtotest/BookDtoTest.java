package com.lms.books.dtotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.books.dto.BookDto;

@SpringBootTest
class BookDtoTest {

	
	BookDto bookDto;
	
	@BeforeEach
	void setup() {
		bookDto = new BookDto();
		bookDto.setBookId(101);
		bookDto.setBookName("book1");
		bookDto.setBookPublisher("A1 publishers");
		bookDto.setBookAuthor("chethan");
	}
	
	@Test
	void getBookIdTest() {
		assertEquals(101,bookDto.getBookId());
	}
	
	@Test
	void getBookNameTest() {
		assertEquals("book1",bookDto.getBookName());
	}
	
	@Test
	void getBookPublisherTest() {
		assertEquals("A1 publishers",bookDto.getBookPublisher());
	}
	
	@Test
	void getBookAuthorTest() {
		assertEquals("chethan",bookDto.getBookAuthor());
	}

}

