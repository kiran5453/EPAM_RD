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

import com.lms.library.datobjects.Book;
import com.lms.library.dto.BookDto;

@FeignClient(name = "book-service")
@LoadBalancerClient(name = "book-service")
public interface BookProxy {

	@GetMapping(value="/books")
	public ResponseEntity<List<Book>> getBooks();
	
	@GetMapping(value="/books/{bookId}")
	public ResponseEntity<Book> getBookWithGivenId(@PathVariable int bookId);
	
	@DeleteMapping(value="/books/{bookId}")
	public ResponseEntity<String> deleteBookWithGivenId(@PathVariable int bookId);
	
	@PostMapping(value="/books")
	public ResponseEntity<String> addBook(@RequestBody @Valid BookDto bookDto);
	
	@PutMapping(value="/books/{bookId}")
	public ResponseEntity<String> updateBook(@PathVariable int bookId, @RequestBody BookDto bookDto);
}
