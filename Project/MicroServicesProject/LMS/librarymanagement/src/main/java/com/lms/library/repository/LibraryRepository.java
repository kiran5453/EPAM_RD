package com.lms.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lms.library.datobjects.LibraryRecord;

public interface LibraryRepository extends CrudRepository<LibraryRecord, Integer> {

	public List<LibraryRecord> findByUserName(String userName);
	public List<LibraryRecord> findByBookId(int bookId);
	public LibraryRecord findByUserNameAndBookId(String username,int bookId);
	public boolean existsByUserNameAndBookId(String userName,int bookId);
}
