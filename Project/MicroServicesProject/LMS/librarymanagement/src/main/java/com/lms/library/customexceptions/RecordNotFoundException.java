package com.lms.library.customexceptions;

public class RecordNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException() {
		super("Record Not Found with given Username and Book Id");
	}
	
}
