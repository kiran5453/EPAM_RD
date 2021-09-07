package com.lms.users.customexceptions;

public class UsernameMismatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameMismatchException() {
		super("Failed to update user...\nUsername cannot be changed");
	}
	
}
