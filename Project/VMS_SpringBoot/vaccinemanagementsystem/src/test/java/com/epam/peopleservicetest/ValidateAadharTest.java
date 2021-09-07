package com.epam.peopleservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.epam.service.PeopleService;

class ValidateAadharTest {

	PeopleService peopleServiceObject = new PeopleService(); 

	@Test
	void validateAadharWith12DigitsAndNotwith01Test() {
		assertEquals(true,peopleServiceObject.validateAadhar("972599404967"));
	}
	
	@Test
	void validateAadharWith12DigitsAndWith01Test() {
		assertEquals(false,peopleServiceObject.validateAadhar("172599404967"));
	}
	
	@Test
	void validateAadharWithLessthan12DigitsTest() {
		assertEquals(false,peopleServiceObject.validateAadhar("72599404967"));
	}
	
	@Test
	void validateAadharWithMorethan12DigitsTest() {
		assertEquals(false,peopleServiceObject.validateAadhar("9172599404967"));
	}

}
