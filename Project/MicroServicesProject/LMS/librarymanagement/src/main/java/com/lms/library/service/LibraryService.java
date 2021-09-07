package com.lms.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.library.customexceptions.RecordNotFoundException;
import com.lms.library.datobjects.Book;
import com.lms.library.datobjects.LibraryRecord;
import com.lms.library.datobjects.User;
import com.lms.library.repository.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	LibraryRepository libraryRepo;
	
	@Autowired
	LibraryBookService libraryBookService;
	
	@Autowired
	LibraryUserService libraryUserService;
	

	LibraryRecord libraryRecord;
	
	@SuppressWarnings("unused")
	public String issueBook(String userName,int bookId) {
		String output;
		User user = libraryUserService.getUserWithGivenUserName(userName);
		Book book = libraryBookService.getBookWithGivenId(bookId).getBody();
		if(!libraryRepo.existsByUserNameAndBookId(userName, bookId)) {
			libraryRecord = new LibraryRecord();
			libraryRecord.setUserName(userName);
			libraryRecord.setBookId(bookId);
			libraryRepo.save(libraryRecord);
			output = "Book with Id: "+bookId+" issued to User with Username: "+userName;
		}
		else
			output = "Book with Id: "+bookId+" already exists for User with Username: "+userName;
		return output;
	}

	public String releaseBook(String userName,int bookId) throws RecordNotFoundException {
		libraryRecord = libraryRepo.findByUserNameAndBookId(userName, bookId);
		if(libraryRecord == null)
			throw new RecordNotFoundException();
		libraryRepo.delete(libraryRecord);
		return "Book with Id: "+bookId+" relased from User with Username: "+userName;
	}

}
