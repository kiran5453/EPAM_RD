package com.epam.repositories;

import org.springframework.data.repository.CrudRepository;

import com.epam.dataobjects.People;

public interface PeopleRepository extends CrudRepository<People,String> {

	People findByAadharNumber(String aadhar);
}
