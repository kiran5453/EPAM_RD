package com.lms.users.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.users.customexceptions.UserNotFoundException;
import com.lms.users.customexceptions.UsernameMismatchException;
import com.lms.users.dataobjects.User;
import com.lms.users.dto.UserDto;
import com.lms.users.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	ModelMapper modelMapper;

	public String addUser(User user) {
		String output = "";
		String userName = user.getUserName();
		if (userRepo.existsById(userName))
			output = "Failed to add User...\nUser with Username: " + userName + " already exists";
		else {
			userRepo.save(user);
			output = "User with Username:" + userName + " added succesfully";
		}
		return output;
	}

	public String updateUser(String userName, User user) throws UsernameMismatchException, UserNotFoundException {
		if (!userRepo.existsById(userName))
			throw new UserNotFoundException("Failed to update User...\nUser with Username: " + userName + " does not exist");
		if (!userName.equals(user.getUserName()))
			throw new UsernameMismatchException();
		userRepo.save(user);
		return "User with UserName:" + userName + " updated succesfully";
	}

	public String deleteUser(String userName) throws UserNotFoundException {
		User user = userRepo.findByUserName(userName);
		if (user == null)
			throw new UserNotFoundException("Failed to delete User...\nUser with Username: " + userName + " does not exists");
		userRepo.deleteById(userName);
		return "User with Username: " + userName + " deleted succesfully";
	}

	public User getUserByUserName(String userName) throws UserNotFoundException {
		User user = userRepo.findByUserName(userName);
		if (user == null)
			throw new UserNotFoundException(
					"Failed to get User...\nUser with Username: " + userName + " does not exist");
		return user;
	}

	public List<User> getUsers() throws UserNotFoundException {
		List<User> users = (List<User>) userRepo.findAll();
		if (users.isEmpty())
			throw new UserNotFoundException("Users not avilable");
		return users;
	}

	public User convertToUser(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

}
