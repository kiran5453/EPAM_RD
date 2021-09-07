package com.lms.users.dtotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.users.dto.UserDto;

@SpringBootTest
class UserDtoTest {

	UserDto userDto;
	
	@BeforeEach
	void setup() {
		userDto =  new UserDto();
		userDto.setUserName("kiran5453");
		userDto.setEmail("saikiran5453@gmail.com");
		userDto.setName("kiran");
	}
	
	@Test
	void getUserNameTest() {
		assertEquals("kiran5453",userDto.getUserName());
	}
	
	@Test
	void getEmailTest() {
		assertEquals("saikiran5453@gmail.com",userDto.getEmail());
	}
	
	@Test
	void getNameTest() {
		assertEquals("kiran",userDto.getName());
	}
}

