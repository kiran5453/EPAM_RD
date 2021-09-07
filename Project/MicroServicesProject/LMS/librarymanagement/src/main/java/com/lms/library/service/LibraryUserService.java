package com.lms.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.library.clients.UserProxy;
import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.LibraryRecord;
import com.lms.library.datobjects.User;
import com.lms.library.dto.UserDto;
import com.lms.library.repository.LibraryRepository;

@Service
public class LibraryUserService {

	@Autowired
	UserProxy userProxy;
	
	@Autowired
	LibraryRepository libraryRepo;
	
	@Autowired
	LibraryBookService libraryBookService;

	public List<User> getUsers(){
		List<User> userList = userProxy.getUsers().getBody();
		userList.forEach(user -> {
			List<Book> books = new ArrayList<>();
			List<LibraryRecord> userRecord = (List<LibraryRecord>) libraryRepo.findByUserName(user.getUserName());
			if(userRecord != null) {
				userRecord.forEach(record -> 
					books.add(libraryBookService.getBookWithGivenId(record.getBookId()).getBody())
				);
			}
			user.setBooks(books);
		});
		return userList;
	}

	public User getUserWithGivenUserName(String userName){
		User user = userProxy.getUserWithGivenUserName(userName).getBody();
		List<Book> books = new ArrayList<>();
		List<LibraryRecord> userRecord = (List<LibraryRecord>) libraryRepo.findByUserName(userName);
		if(userRecord != null) {
			userRecord.forEach(record -> 
				books.add(libraryBookService.getBookWithGivenId(record.getBookId()).getBody())
			);
		}
		user.setBooks(books);
		return user;
	}
	
	public ResponseEntity<String> deleteUserWithGivenUserName(String userName){
		libraryRepo.deleteAll(libraryRepo.findByUserName(userName));
		return userProxy.deleteUserWithGivenUserName(userName);
	}
	
	public ResponseEntity<String> addUser(UserDto userDto){
		return userProxy.addUser(userDto);
	}
	
	public ResponseEntity<String> updateUser(String userName,UserDto userDto){
		return userProxy.updateUser(userName,userDto);
	}
}
