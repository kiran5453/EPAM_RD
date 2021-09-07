package com.lms.books.repository;

import org.springframework.data.repository.CrudRepository;

import com.lms.books.dataobjects.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	public Book findByBookId(int bookId);
}
