package com.epam.dtotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.PeopleDTO;

@ExtendWith(MockitoExtension.class)
class PeopleDTOTest {

	@InjectMocks
	PeopleDTO personDto;
	
	@BeforeEach
	void setup() {
		
		personDto.setAadharNumber("972599404967");
		personDto.setName("kiran");
		personDto.setAge(21);
		personDto.setFirstDoseDate("2021-07-10");
		personDto.setSecondDoseDate(null);
		personDto.setVaccineType("covaxin");
	}
	@Test
	void getAadharNumberTest() {
		assertEquals("972599404967",personDto.getAadharNumber());
	}
	@Test
	void getpersonNameTest() {
		assertEquals("kiran",personDto.getName());
	}
	@Test
	void getpersonAgeTest() {
		assertEquals(21,personDto.getAge());
	}
	@Test
	void getFirstDoseDateTest() {
		assertEquals("2021-07-10",personDto.getFirstDoseDate());
	}
	@Test
	void getSecondDoseDateTest() {
		assertEquals(null,personDto.getSecondDoseDate());
	}
}
