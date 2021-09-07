package com.lms.users.controllertest;

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
import com.lms.users.customexceptions.UserNotFoundException;
import com.lms.users.customexceptions.UsernameMismatchException;
import com.lms.users.dataobjects.User;
import com.lms.users.dto.UserDto;
import com.lms.users.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	UserService userService;


	private UserDto userDto;
	private User user;

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
	}

	@Test
	void addUserTest() throws Exception {
		when(userService.addUser(Mockito.any())).thenReturn("User with Username:" + user.getUserName() + " added succesfully");
		String userDtoJson = new ObjectMapper().writeValueAsString(userDto);
		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(userDtoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User with Username:" + user.getUserName() + " added succesfully"));
	}

	@Test
	void updateUserTest() throws Exception {
		when(userService.updateUser(Mockito.any(),Mockito.any()))
			.thenReturn("User with Username:" + user.getUserName() + " updated succesfully");
		String userDtoJson = new ObjectMapper().writeValueAsString(userDto);
		mvc.perform(put("/users/kiran5453").contentType(MediaType.APPLICATION_JSON)
				.content(userDtoJson)).andExpect(status().isOk())
		.andExpect(jsonPath("$").value("User with Username:" + user.getUserName() + " updated succesfully"));
	}

	@Test
	void getUsersDataTest() throws Exception {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userService.getUsers()).thenReturn(userList);
		mvc.perform(get("/users")).andExpect(status().isOk())
			.andExpect(jsonPath("$[0].userName").value("kiran5453"));
	}

	@Test
	void deleteUserTest() throws Exception {
		when(userService.deleteUser(Mockito.any()))
			.thenReturn("User with Username: " + user.getUserName() + " deleted succesfully");
		mvc.perform(delete("/users/kiran5453")).andExpect(status().isOk())
			.andExpect(jsonPath("$").value("User with Username: " + user.getUserName() + " deleted succesfully"));
	}
	
	@Test
	void getUserTest() throws Exception {
		when(userService.getUserByUserName(Mockito.any())).thenReturn(user);
		mvc.perform(get("/users/kiran5453")).andExpect(status().isOk())
		.andExpect(jsonPath("$.userName").value("kiran5453"));
	}
	
	@Test
	void invalidArgumentsExceptionTest() throws Exception {
		userDto.setUserName(null);
		String userDtoJson = new ObjectMapper().writeValueAsString(userDto);
		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(userDtoJson))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.details").value("Username should not be null, "));
	}
	
	@Test
	void userNotFoundExceptionTest() throws Exception {
		when(userService.getUserByUserName(Mockito.any())).thenThrow(UserNotFoundException.class);
		mvc.perform(get("/users/kiran5454")).andExpect(status().isBadRequest());
	}
	
	@Test
	void userNameMismatchExceptionTest() throws Exception {
		when(userService.updateUser(Mockito.any(),Mockito.any())).thenThrow(UsernameMismatchException.class);
		String bookDtoJson = new ObjectMapper().writeValueAsString(userDto);
		mvc.perform(put("/users/kiran5454").contentType(MediaType.APPLICATION_JSON)
				.content(bookDtoJson)).andExpect(status().isBadRequest());
		}
}
