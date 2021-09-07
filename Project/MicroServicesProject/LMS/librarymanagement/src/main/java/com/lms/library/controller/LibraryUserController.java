package com.lms.library.controller;

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

import com.lms.library.datobjects.User;
import com.lms.library.dto.UserDto;
import com.lms.library.service.LibraryUserService;

@RestController
public class LibraryUserController {
	
	@Autowired
	LibraryUserService libraryUserService;

	@GetMapping(value = "/library/users/{userName}")
	public ResponseEntity<User> displayUserWithGivenUserName(@PathVariable String userName) {
		return new ResponseEntity<>(libraryUserService.getUserWithGivenUserName(userName),HttpStatus.OK);
	}

	@GetMapping(value = "/library/users")
	public ResponseEntity<List<User>> displayUsers() {
		return new ResponseEntity<>(libraryUserService.getUsers(),HttpStatus.OK);
	}

	@DeleteMapping(value = "/library/users/{userName}")
	public ResponseEntity<String> deleteUserWithGivenUserName(@PathVariable String userName) {
		return libraryUserService.deleteUserWithGivenUserName(userName);
	}
	
	@PostMapping(value="/library/users")
	public ResponseEntity<String> addUser(@RequestBody @Valid UserDto userDto){
		return libraryUserService.addUser(userDto);
	}
	
	@PutMapping(value="/library/users/{userName}")
	public ResponseEntity<String> updateUser(@PathVariable String userName,@RequestBody UserDto userDto){
		return libraryUserService.updateUser(userName,userDto);
	}
}
