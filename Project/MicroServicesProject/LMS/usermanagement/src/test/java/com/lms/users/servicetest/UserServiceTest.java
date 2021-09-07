package com.lms.users.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.lms.users.customexceptions.UserNotFoundException;
import com.lms.users.customexceptions.UsernameMismatchException;
import com.lms.users.dataobjects.User;
import com.lms.users.dto.UserDto;
import com.lms.users.repository.UserRepository;
import com.lms.users.service.UserService;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Mock
	ModelMapper mapper;
	
	private User user;

	@BeforeEach
	void setup() {
		
		user = new User();
		user.setUserName("kiran5453");
		user.setEmail("saikiran5453@gmail.com");
		user.setName("kiran");
		
	}
	
	@Test
	void addUserTest() {
		when(userRepository.existsById("kiran5453")).thenReturn(false);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals("User with Username:" + user.getUserName() + " added succesfully", userService.addUser(user));
	}
	
	@Test
	void addBookTestWithBook() {
		when(userRepository.existsById("kiran5453")).thenReturn(true);
		assertEquals("Failed to add User...\nUser with Username: " + user.getUserName() + " already exists", userService.addUser(user));
	}
	
	@Test
	void updateUserTestWithoutException() throws UsernameMismatchException, UserNotFoundException {
		when(userRepository.existsById("kiran5453")).thenReturn(true);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals("User with UserName:" + user.getUserName() + " updated succesfully", userService.updateUser("kiran5453",user));
	}
	
	@Test
	void updateUserTestWithBookException() throws UsernameMismatchException, UserNotFoundException {
		when(userRepository.existsById("kiran5453")).thenReturn(false);
		assertThrows(UserNotFoundException.class, () -> {
			userService.updateUser("kiran5453",user);
		});
	}
	
	@Test
	void updateUserTestWithUsernameException() throws UsernameMismatchException, UserNotFoundException {
		when(userRepository.existsById("kiran5454")).thenReturn(true);
		assertThrows(UsernameMismatchException.class, () -> {
			userService.updateUser("kiran5454",user);
		});
	}
	
	@Test
	void deleteBookTestWithBook() throws UserNotFoundException {
		when(userRepository.findByUserName("kiran5453")).thenReturn(user);
		doNothing().when(userRepository).deleteById("kiran5453");
		assertEquals("User with Username: " + user.getUserName() + " deleted succesfully" , userService.deleteUser("kiran5453"));
	}
	
	@Test
	void deleteBookTestWithoutBook() throws UserNotFoundException {
		when(userRepository.findByUserName("kiran5454")).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> {
			userService.deleteUser("kiran5454");
		});
	}
	
	@Test
	void getUserByUsernameTestWithBook() throws UserNotFoundException {
		when(userRepository.findByUserName("kiran5453")).thenReturn(user);
		assertEquals(user,userService.getUserByUserName("kiran5453"));
	}
	
	@Test
	void getBookByIdTestWithoutBook() throws UserNotFoundException {
		when(userRepository.findByUserName("kiran5454")).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> {
			userService.getUserByUserName("kiran5454");
		});
	}
	
	@Test
	void getBooksTestWithBooks() throws UserNotFoundException {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userRepository.findAll()).thenReturn(userList);
		assertEquals(user,userService.getUsers().get(0));
	}
	
	@Test
	void getBooksTestWithoutBooks() throws UserNotFoundException {
		when(userRepository.findAll()).thenReturn(new ArrayList<>());
		assertThrows(UserNotFoundException.class, () -> {
			userService.getUsers();
		});
	}
	
	@Test
	void modelMapperTest() {
		UserDto userDto = new UserDto();
		userDto.setUserName("kiran5453");
		userDto.setEmail("saikiran5453@gmail.com");
		userDto.setName("kiran");
		when(mapper.map(userDto, User.class)).thenReturn(user);
		assertEquals(user,userService.convertToUser(userDto));
	}
}
