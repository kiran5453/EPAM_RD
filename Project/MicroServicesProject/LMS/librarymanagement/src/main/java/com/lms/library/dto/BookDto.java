package com.lms.library.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookDto {

	@NotNull(message = "Book Id should not be null")
	private Integer bookId;
	@NotNull(message = "Book Name should not be null")
	private String bookName;
	private String bookPublisher;
	private String bookAuthor;
	
}
