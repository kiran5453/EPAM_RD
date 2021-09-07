package com.lms.books.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.books.customexceptions.BookNotFoundException;
import com.lms.books.customexceptions.IdMismatchException;
import com.lms.books.dataobjects.Book;
import com.lms.books.dto.BookDto;
import com.lms.books.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepo;

	@Autowired
	ModelMapper modelMapper;

	public String addBook(Book book) {
		String output = "";
		int bookId = book.getBookId();
		if (bookRepo.existsById(bookId))
			output = "Failed to add Book...\nBook with Id: " + bookId + " already exists";
		else {
			bookRepo.save(book);
			output = "Book with Id:" + bookId + " added succesfully";
		}
		return output;
	}

	public String updateBook(int bookId, Book book) throws IdMismatchException,BookNotFoundException {
		if (!bookRepo.existsById(bookId))
			throw new BookNotFoundException("Failed to update book...\nBook with Id: " + bookId + " does not exist");
		if (bookId!= book.getBookId())
			throw new IdMismatchException();
		bookRepo.save(book);
		return "Book with Id:" + bookId + " updated succesfully";
	}

	public String deleteBook(int bookId) throws BookNotFoundException {
		Book book = bookRepo.findByBookId(bookId);
		if (book == null)
			throw new BookNotFoundException("Failed to delete book...\nBook with Id: " + bookId + " does not exist");
		bookRepo.deleteById(bookId);
		return "Book with Id: " + bookId + " deleted succesfully";
	}

	public Book getBookById(int bookId) throws BookNotFoundException {
		Book book = bookRepo.findByBookId(bookId);
		if (book == null)
			throw new BookNotFoundException("Failed to get book...\nBook with Id: " + bookId + " does not exist");
		return book;
	}

	public List<Book> getBooks() throws BookNotFoundException {
		List<Book> bookList = (List<Book>) bookRepo.findAll();
		if (bookList.isEmpty())
			throw new BookNotFoundException("No books available");
		return bookList;
	}

	public Book convertToBook(BookDto bookDto) {
		return modelMapper.map(bookDto, Book.class);
	}

}
