package com.lms.library.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lms.library.controller.LibraryController;
import com.lms.library.customexceptions.RecordNotFoundException;
import com.lms.library.repository.LibraryRepository;
import com.lms.library.service.LibraryService;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

	@Autowired
	LibraryController libraryController;
	
	@MockBean
	LibraryService libraryService;
	
	@Autowired
	MockMvc mockMvc;
	
	private static final String LIB_URI = "http://localhost:8084/library";
	
	@BeforeEach
	void setup() {
		
	}
	
	@Test
	void contextLoads() throws Exception {
		assertThat(libraryController).isNotNull();
	}
	
	@Test
	void issueBookTest() throws Exception {
		when(libraryService.issueBook("kiran5453", 101))
			.thenReturn("Book with Id: 101 issued to User with Username: kiran5453");
		mockMvc.perform(MockMvcRequestBuilders
				.post(LIB_URI+"/users/kiran5453/books/101")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book with Id: 101 issued to User with Username: kiran5453"));
	}
	
	@Test
	void releaseBookTest() throws Exception {
		when(libraryService.releaseBook("kiran5453", 101))
			.thenReturn("Book with Id: 101 released from User with Username: kiran5453");
		mockMvc.perform(MockMvcRequestBuilders
				.delete(LIB_URI+"/users/kiran5453/books/101")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book with Id: 101 released from User with Username: kiran5453"));
	}
	
	
}
