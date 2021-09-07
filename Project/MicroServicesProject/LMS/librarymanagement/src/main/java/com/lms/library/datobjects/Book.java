package com.lms.library.datobjects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Book {
	
	private int bookId;
	private String bookName;
	private String bookPublisher;
	private String bookAuthor;
}
