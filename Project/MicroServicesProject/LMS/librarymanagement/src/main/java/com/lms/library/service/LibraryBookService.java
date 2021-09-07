package com.lms.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.library.clients.BookProxy;
import com.lms.library.datobjects.Book;
import com.lms.library.dto.BookDto;
import com.lms.library.repository.LibraryRepository;


@Service
public class LibraryBookService {

	@Autowired
	BookProxy bookProxy;
	
	@Autowired
	LibraryRepository libraryRepo;

	public ResponseEntity<List<Book>> getBooks(){
		return bookProxy.getBooks();
	}
	
	public ResponseEntity<Book> getBookWithGivenId(int bookId){
		return bookProxy.getBookWithGivenId(bookId);
	}
	
	public ResponseEntity<String> deleteBookWithGivenId(int bookId){
		libraryRepo.deleteAll(libraryRepo.findByBookId(bookId));
		return bookProxy.deleteBookWithGivenId(bookId);
	}
	
	public ResponseEntity<String> addBook(BookDto bookDto){
		return bookProxy.addBook(bookDto);
	}
	
	public ResponseEntity<String> updateBook(int bookId,BookDto bookDto){
		return bookProxy.updateBook(bookId,bookDto);
	}
	
}
