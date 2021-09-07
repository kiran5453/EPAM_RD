package com.lms.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.library.datobjects.Book;
import com.lms.library.dto.BookDto;
import com.lms.library.service.LibraryBookService;

@RestController
public class LibraryBookController {
	
	@Autowired
	LibraryBookService libraryBookService;
	
	@Autowired
	Environment env;

	@GetMapping(value = "/library/books/{bookId}")
	public ResponseEntity<Book> displayBookWithGivenId(@PathVariable int bookId) {
		return libraryBookService.getBookWithGivenId(bookId);
	}

	@GetMapping(value = "/library/books")
	public ResponseEntity<List<Book>> displayBooks() {
		return libraryBookService.getBooks();
	}

	@DeleteMapping(value = "/library/books/{bookId}")
	public ResponseEntity<String> deleteBookWithGivenId(@PathVariable int bookId) {
		return libraryBookService.deleteBookWithGivenId(bookId);
	}
	
	@PostMapping(value="/library/books")
	public ResponseEntity<String> addBook(@RequestBody @Valid BookDto bookDto){
		return libraryBookService.addBook(bookDto);
	}
	
	@PutMapping(value="/library/books/{bookId}")
	public ResponseEntity<String> updateBook(@PathVariable int bookId,@RequestBody BookDto bookDto){
		return libraryBookService.updateBook(bookId,bookDto);
	}

}
