package com.lms.library.clients;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lms.library.datobjects.User;
import com.lms.library.dto.UserDto;

@FeignClient(name = "user-service")
@LoadBalancerClient(name = "user-service")
public interface UserProxy {

	@GetMapping(value="/users")
	public ResponseEntity<List<User>> getUsers();
	
	@GetMapping(value="/users/{userName}")
	public ResponseEntity<User> getUserWithGivenUserName(@PathVariable String userName);
	
	@DeleteMapping(value="/users/{userName}")
	public ResponseEntity<String> deleteUserWithGivenUserName(@PathVariable String userName);
	
	@PostMapping(value="/users")
	public ResponseEntity<String> addUser(@RequestBody @Valid UserDto userDto);
	
	@PutMapping(value="/users/{userName}")
	public ResponseEntity<String> updateUser(@PathVariable String userName, @RequestBody UserDto userDto);
}
