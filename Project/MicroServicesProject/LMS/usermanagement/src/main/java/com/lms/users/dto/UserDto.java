package com.lms.users.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

	@NotNull(message = "Username should not be null")
	private String userName;
	@NotNull(message = "Email should not be null")
	private String email;
	@NotNull(message = "Name should not be null")
	private String name;
	
}
