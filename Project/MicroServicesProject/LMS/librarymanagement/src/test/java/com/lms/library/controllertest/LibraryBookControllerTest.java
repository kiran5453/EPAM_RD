package com.lms.library.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.library.controller.LibraryBookController;
import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.LibraryRecord;
import com.lms.library.dto.BookDto;
import com.lms.library.service.LibraryBookService;

import feign.FeignException.FeignClientException;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryBookControllerTest {

	
	@MockBean
	LibraryBookService bookService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	LibraryBookController bookController;
	
	Book book;
	LibraryRecord libraryRecord;
	BookDto bookDto;
	
	private static final String BOOK_URI = "http://localhost:8084/library/books";
	
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
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);

	}
	
	@Test
	void contextLoads() throws Exception {
	assertThat(bookController).isNotNull();
	}
	
	@Test
	void displayBookWithGivenIdTest() throws Exception {
		when(bookService.getBookWithGivenId(101)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.get(BOOK_URI+"/101")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(101));
	}
	
	@Test
	void displayBooksTest() throws Exception {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		when(bookService.getBooks()).thenReturn(new ResponseEntity<>(bookList,HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.get(BOOK_URI)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].bookId", is(101)));
	}
	
	@Test
	void deleteBookWithGivenIdTest() throws Exception {
		when(bookService.deleteBookWithGivenId(101))
			.thenReturn(new ResponseEntity<>("Book with Id: " + book.getBookId() + " deleted succesfully",HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.delete(BOOK_URI+"/101")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book with Id: " + book.getBookId() + " deleted succesfully"));
	}
	
	@Test
	void addBookTest() throws Exception {
		when(bookService.addBook(Mockito.any())).thenReturn(new ResponseEntity<>("Book with Id: " + book.getBookId() + " added succesfully",HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.post(BOOK_URI)
				.content(asJsonString(bookDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book with Id: " + book.getBookId() + " added succesfully"));
	}

	@Test
	void updateBookTest() throws Exception {
		when(bookService.updateBook(Mockito.anyInt(),Mockito.any()))
			.thenReturn(new ResponseEntity<>("Book with Id: " + book.getBookId() + " updated succesfully",HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.put(BOOK_URI+"/101")
				.content(asJsonString(bookDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book with Id: " + book.getBookId() + " updated succesfully"));
	}
	
	@Test
	void addBookTestWithException() throws Exception {
		bookDto.setBookId(null);
		mockMvc.perform(MockMvcRequestBuilders
				.post(BOOK_URI)
				.content(asJsonString(bookDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.details").value("Book Id should not be null, "));
	}
	@Test
	void displayBookWithGivenIdWithExceptionTest() throws Exception {
		when(bookService.getBookWithGivenId(101)).thenThrow(FeignClientException.class);
		mockMvc.perform(MockMvcRequestBuilders
				.get(BOOK_URI+"/101")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
	}
	
}
