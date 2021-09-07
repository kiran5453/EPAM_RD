package com.lms.books.customexceptions;

public class IdMismatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdMismatchException() {
		super("Failed to update...\nBook Id cannot be changed");
	}
	
}
