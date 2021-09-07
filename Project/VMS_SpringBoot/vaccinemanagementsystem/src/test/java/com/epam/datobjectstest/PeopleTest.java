package com.epam.datobjectstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dataobjects.Address;
import com.epam.dataobjects.People;

@ExtendWith(MockitoExtension.class)
class PeopleTest {

	@InjectMocks
	People person;
	@Mock
	Address address;
	private List<Address> list = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		
		person.setAadharNumber("972599404967");
		person.setName("kiran");
		person.setAge(21);
		person.setFirstDoseDate(LocalDate.parse("2021-07-10"));
		person.setSecondDoseDate(null);
		person.setVaccineType("covaxin");
		address.setType("Home");
		address.setStreet("gdpd");
		address.setCity("gnt");
		address.setPincode(522403);
		address.setState("ap");
		list.add(address);
		person.setAddresses(list);
	}
	@Test
	void getAadharNumberTest() {
		assertEquals("972599404967",person.getAadharNumber());
	}
	@Test
	void getpersonNameTest() {
		assertEquals("kiran",person.getName());
	}
	@Test
	void getpersonAgeTest() {
		assertEquals(21,person.getAge());
	}
	@Test
	void getFirstDoseDateTest() {
		assertEquals(LocalDate.parse("2021-07-10"),person.getFirstDoseDate());
	}
	@Test
	void getSecondDoseDateTest() {
		assertEquals(null,person.getSecondDoseDate());
	}
	@Test
	void  getAddressTest() {
		assertEquals(list,person.getAddresses());
	}

}
