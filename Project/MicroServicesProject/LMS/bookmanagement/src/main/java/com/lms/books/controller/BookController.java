package com.lms.books.controller;

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

import com.lms.books.customexceptions.IdMismatchException;
import com.lms.books.customexceptions.BookNotFoundException;
import com.lms.books.dataobjects.Book;
import com.lms.books.dto.BookDto;
import com.lms.books.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping(value = "books")
	public ResponseEntity<List<Book>> displayBooks() throws BookNotFoundException {
		return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
	}

	@GetMapping(value = "books/{bookId}")
	public ResponseEntity<Book> displayBookWithGivenId(@PathVariable int bookId) throws BookNotFoundException {
		return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
	}

	@PostMapping(value = "books")
	public ResponseEntity<String> addBook(@RequestBody @Valid BookDto bookDto) {
		Book book = bookService.convertToBook(bookDto);
		String response = bookService.addBook(book);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(value = "books/{bookId}")
	public ResponseEntity<String> updateBook(@PathVariable int bookId,@RequestBody BookDto bookDto) throws IdMismatchException, BookNotFoundException {
		Book book = bookService.convertToBook(bookDto);
		String response = bookService.updateBook(bookId,book);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "books/{bookId}")
	public ResponseEntity<String> deleteBookWithGivenId(@PathVariable int bookId) throws BookNotFoundException {
		return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
	}
}
