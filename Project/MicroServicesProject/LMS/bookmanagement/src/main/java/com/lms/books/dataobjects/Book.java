package com.lms.books.dataobjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class Book {
	
	@Id
	private int bookId;
	private String bookName;
	private String bookPublisher;
	private String bookAuthor;
}
