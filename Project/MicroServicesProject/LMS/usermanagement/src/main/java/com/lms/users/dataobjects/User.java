package com.lms.users.dataobjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class User {

	@Id
	private String userName;
	private String email;
	private String name;
}
