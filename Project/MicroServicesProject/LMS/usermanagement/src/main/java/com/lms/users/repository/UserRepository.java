package com.lms.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.lms.users.dataobjects.User;

public interface UserRepository extends CrudRepository<User,String>{

	public User findByUserName(String userName);
}
