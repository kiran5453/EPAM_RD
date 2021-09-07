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
import com.lms.library.controller.LibraryUserController;
import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.LibraryRecord;
import com.lms.library.datobjects.User;
import com.lms.library.dto.UserDto;
import com.lms.library.service.LibraryUserService;


@SpringBootTest
@AutoConfigureMockMvc
class LibraryUserControllerTest {

	@Autowired
	LibraryUserController userController;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	LibraryUserService userService;
	
	UserDto userDto;
	User user;
	LibraryRecord libraryRecord;
	Book book;
	
	private static final String USER_URI = "http://localhost:8084/library/users";
	
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
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		user.setBooks(bookList);
	}
	
	@Test
	void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);

	}
	
	@Test
	void displayUserWithGivenUserNameTest() throws Exception {
		when(userService.getUserWithGivenUserName("kiran5453")).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders
				.get(USER_URI+"/kiran5453")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("kiran5453"));
	}

	@Test
	void displayUsersTest() throws Exception {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userService.getUsers()).thenReturn(userList);
		mockMvc.perform(MockMvcRequestBuilders
				.get(USER_URI)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].userName", is("kiran5453")));
	}
	
	@Test
	void deleteUserWithGivenUserNameTest() throws Exception {
		when(userService.deleteUserWithGivenUserName("kiran5453"))
			.thenReturn(new ResponseEntity<>("User with Username: " + user.getUserName() + " deleted succesfully",HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.delete(USER_URI+"/kiran5453")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User with Username: " + user.getUserName() + " deleted succesfully"));
	}
	
	@Test
	void addUserTest() throws Exception {
		when(userService.addUser(Mockito.any()))
			.thenReturn(new ResponseEntity<>("User with Username: " + user.getUserName() + " added succesfully",HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.post(USER_URI)
				.content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User with Username: " + user.getUserName() + " added succesfully"));
	}

	@Test
	void updateUserTest() throws Exception {
		when(userService.updateUser(Mockito.any(),Mockito.any()))
			.thenReturn(new ResponseEntity<>("User with Username: " + user.getUserName() + " updated succesfully",HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders
				.put(USER_URI+"/kiran5453")
				.content(asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User with Username: " + user.getUserName() + " updated succesfully"));
	}
}
