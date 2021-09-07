package com.lms.books.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.lms.books.customexceptions.BookNotFoundException;
import com.lms.books.customexceptions.IdMismatchException;
import com.lms.books.dataobjects.Book;
import com.lms.books.dto.BookDto;
import com.lms.books.repository.BookRepository;
import com.lms.books.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	BookService bookService;

	@Mock
	BookRepository bookRepository;

	@Mock
	ModelMapper mapper;
	
	private Book book;

	@BeforeEach
	void setup() {
		
		book = new Book();
		book.setBookId(101);
		book.setBookName("book1");
		book.setBookPublisher("A1 publishers");
		book.setBookAuthor("chethan");
		
	}
	
	@Test
	void addBookTest() {
		when(bookRepository.existsById(101)).thenReturn(false);
		when(bookRepository.save(book)).thenReturn(book);
		assertEquals("Book with Id:" + book.getBookId() + " added succesfully", bookService.addBook(book));
	}
	
	@Test
	void addBookTestWithBook() {
		when(bookRepository.existsById(101)).thenReturn(true);
		assertEquals("Failed to add Book...\nBook with Id: " + book.getBookId() + " already exists", bookService.addBook(book));
	}
	
	@Test
	void updateBookTestWithoutException() throws IdMismatchException, BookNotFoundException {
		when(bookRepository.existsById(101)).thenReturn(true);
		when(bookRepository.save(book)).thenReturn(book);
		assertEquals("Book with Id:" + book.getBookId() + " updated succesfully", bookService.updateBook(101,book));
	}
	
	@Test
	void updateBookTestWithBookException() throws IdMismatchException, BookNotFoundException {
		when(bookRepository.existsById(102)).thenReturn(false);
		assertThrows(BookNotFoundException.class, () -> {
			bookService.updateBook(102,book);
		});
	}
	
	@Test
	void updateBookTestWithIdException() throws IdMismatchException, BookNotFoundException {
		when(bookRepository.existsById(102)).thenReturn(true);
		assertThrows(IdMismatchException.class, () -> {
			bookService.updateBook(102,book);
		});
	}
	
	@Test
	void deleteBookTestWithBook() throws BookNotFoundException {
		when(bookRepository.findByBookId(101)).thenReturn(book);
		doNothing().when(bookRepository).deleteById(101);
		assertEquals("Book with Id: " + book.getBookId() + " deleted succesfully" , bookService.deleteBook(101));
	}
	
	@Test
	void deleteBookTestWithoutBook() throws BookNotFoundException {
		when(bookRepository.findByBookId(102)).thenReturn(null);
		assertThrows(BookNotFoundException.class, () -> {
			bookService.deleteBook(102);
		});
	}
	
	@Test
	void getBookByIdTestWithBook() throws BookNotFoundException {
		when(bookRepository.findByBookId(101)).thenReturn(book);
		assertEquals(book,bookService.getBookById(101));
	}
	
	@Test
	void getBookByIdTestWithoutBook() throws BookNotFoundException {
		when(bookRepository.findByBookId(102)).thenReturn(null);
		assertThrows(BookNotFoundException.class, () -> {
			bookService.getBookById(102);
		});
	}
	
	@Test
	void getBooksTestWithBooks() throws BookNotFoundException {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		when(bookRepository.findAll()).thenReturn(bookList);
		assertEquals(book,bookService.getBooks().get(0));
	}
	
	@Test
	void getBooksTestWithoutBooks() throws BookNotFoundException {
		when(bookRepository.findAll()).thenReturn(new ArrayList<>());
		assertThrows(BookNotFoundException.class, () -> {
			bookService.getBooks();
		});
	}
	
	@Test
	void modelMapperTest() {
		BookDto bookDto = new BookDto();
		bookDto.setBookId(101);
		bookDto.setBookName("book1");
		bookDto.setBookPublisher("A1 publishers");
		bookDto.setBookAuthor("chethan");
		when(mapper.map(bookDto, Book.class)).thenReturn(book);
		assertEquals(book,bookService.convertToBook(bookDto));
	}
}
