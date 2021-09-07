package com.lms.users.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.users.customexceptions.UsernameMismatchException;
import com.lms.users.customexceptions.UserNotFoundException;
import com.lms.users.dataobjects.User;
import com.lms.users.dto.UserDto;
import com.lms.users.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "users")
	public ResponseEntity<List<User>> displayUsers() throws UserNotFoundException {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping(value = "users/{userName}")
	public ResponseEntity<User> displayUserWithGivenUserName(@PathVariable String userName)
			throws UserNotFoundException {
		return new ResponseEntity<>(userService.getUserByUserName(userName), HttpStatus.OK);
	}

	@PostMapping(value = "users")
	public ResponseEntity<String> addUser(@RequestBody @Valid UserDto userDto) {
		User user = userService.convertToUser(userDto);
		String response = userService.addUser(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "users/{userName}")
	public ResponseEntity<String> updateUser(@PathVariable String userName, @RequestBody UserDto userDto) throws UsernameMismatchException, UserNotFoundException {
		User user = userService.convertToUser(userDto);
		String response = userService.updateUser(userName,user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "users/{userName}")
	public ResponseEntity<String> deleteUserWithGivenUserName(@PathVariable String userName)
			throws UserNotFoundException {
		return new ResponseEntity<>(userService.deleteUser(userName), HttpStatus.OK);
	}
}
