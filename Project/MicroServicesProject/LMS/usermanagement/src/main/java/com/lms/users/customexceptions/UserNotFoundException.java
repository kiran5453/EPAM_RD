package com.lms.users.customexceptions;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5766553230236569488L;

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
