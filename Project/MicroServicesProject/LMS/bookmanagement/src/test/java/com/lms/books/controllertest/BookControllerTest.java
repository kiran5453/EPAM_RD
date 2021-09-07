package com.lms.books.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.books.customexceptions.BookNotFoundException;
import com.lms.books.customexceptions.IdMismatchException;
import com.lms.books.dataobjects.Book;
import com.lms.books.dto.BookDto;
import com.lms.books.service.BookService;

@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {
	
	@Autowired
	MockMvc mvc;

	@MockBean
	BookService bookService;


	private BookDto bookDto;
	private Book book;

	@BeforeEach
	void setup() {
		bookDto = new BookDto();
		bookDto.setBookId(101);
		bookDto.setBookAuthor("chethan");
		bookDto.setBookName("book1");
		bookDto.setBookPublisher("A1 publishers");
		book = new Book();
		book.setBookId(101);
		book.setBookName("book1");
		book.setBookPublisher("A1 publishers");
		book.setBookAuthor("chethan");
	}

	@Test
	void addBookTest() throws Exception {
		when(bookService.addBook(Mockito.any())).thenReturn("Book with Id:" + book.getBookId() + " added succesfully");
		String bookDtoJson = new ObjectMapper().writeValueAsString(bookDto);
		mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(bookDtoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book with Id:" + book.getBookId() + " added succesfully"));
	}

	@Test
	void updateBookTest() throws Exception {
		when(bookService.updateBook(Mockito.anyInt(),Mockito.any()))
			.thenReturn("Book with Id:" + book.getBookId() + " updated succesfully");
		String bookDtoJson = new ObjectMapper().writeValueAsString(bookDto);
		mvc.perform(put("/books/101").contentType(MediaType.APPLICATION_JSON)
				.content(bookDtoJson)).andExpect(status().isOk())
		.andExpect(jsonPath("$").value("Book with Id:" + book.getBookId() + " updated succesfully"));
	}

	@Test
	void getBooksDataTest() throws Exception {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		when(bookService.getBooks()).thenReturn(bookList);
		mvc.perform(get("/books")).andExpect(status().isOk())
			.andExpect(jsonPath("$[0].bookId").value(101));
	}

	@Test
	void deleteBookTest() throws Exception {
		when(bookService.deleteBook(Mockito.anyInt()))
			.thenReturn("Book with Id: " + book.getBookId() + " deleted succesfully");
		mvc.perform(delete("/books/101")).andExpect(status().isOk())
			.andExpect(jsonPath("$").value("Book with Id: " + book.getBookId() + " deleted succesfully"));
	}
	
	@Test
	void getBookTest() throws Exception {
		when(bookService.getBookById(101)).thenReturn(book);
		mvc.perform(get("/books/101")).andExpect(status().isOk())
		.andExpect(jsonPath("$.bookId").value(101));
	}
	
	@Test
	void invalidArgumentsExceptionTest() throws Exception {
		bookDto.setBookId(null);
		String bookDtoJson = new ObjectMapper().writeValueAsString(bookDto);
		mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(bookDtoJson))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.details").value("Book Id should not be null, "));
	}
	
	@Test
	void bookNotFoundExceptionTest() throws Exception {
		when(bookService.getBookById(102)).thenThrow(BookNotFoundException.class);
		mvc.perform(get("/books/102")).andExpect(status().isBadRequest());
	}
	
	@Test
	void idMismatchExceptionTest() throws Exception {
		when(bookService.updateBook(Mockito.anyInt(),Mockito.any())).thenThrow(IdMismatchException.class);
		String bookDtoJson = new ObjectMapper().writeValueAsString(bookDto);
		mvc.perform(put("/books/102").contentType(MediaType.APPLICATION_JSON)
				.content(bookDtoJson)).andExpect(status().isBadRequest());
		}
}
