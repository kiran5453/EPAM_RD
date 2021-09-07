package com.epam.peopleservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.epam.repositories.PeopleRepository;
import com.epam.service.PeopleService;

@ExtendWith(MockitoExtension.class)
class PeopleDatabaseTest {

	private People peopleObject;
	@Mock
	private PeopleRepository peopleRepo;
	@InjectMocks
	private PeopleService peopleService;
	
	@BeforeEach
	void setup() {
	
		peopleObject = new People();
		peopleObject.setName("kiran");
		peopleObject.setAadharNumber("972599404967");
		peopleObject.setAge(20);
		peopleObject.setFirstDoseDate(null);
		peopleObject.setSecondDoseDate(null);
		peopleObject.setVaccineType(null);
	}
	
	@Test
	void insertPeopleToDatabaseTest() {
		
		when(peopleRepo.save(peopleObject)).thenReturn(peopleObject);
		peopleService.insertPeopleDataToDatabase("972599404967", peopleObject);
		verify(peopleRepo,atLeastOnce()).save(peopleObject);
	}
	
	@Test
	void getPeopleDataFromDatabaseTest() {
		List<People> peopleList = new ArrayList<People>();
		peopleList.add(peopleObject);
		when(peopleRepo.findAll()).thenReturn(peopleList);
		assertEquals(peopleObject,peopleService.getPeopleDataFromDatabase().get(0));
	}

	@Test
	void findPeopleInDatabaseWithPersonTest() {
		when(peopleRepo.findByAadharNumber("972599404967")).thenReturn(peopleObject);
		assertEquals(peopleObject,peopleService.findPeopleInDatabase("972599404967"));
	}
	
	@Test
	void findPeopleInDatabaseWithoutPersonTest() {
		when(peopleRepo.findByAadharNumber("982599404967")).thenReturn(null);
		assertEquals(null,peopleService.findPeopleInDatabase("982599404967"));
	}
	
	@Test
	void insertAddressToDatabaseTest() {
		List<Address> addresses = new ArrayList<>();
		Address address = new Address();
		address.setCity("gnt");
		address.setStreet("gdpd");
		address.setState("ap");
		address.setPincode(522403);
		address.setType("home");
		address.setPeople(peopleObject);
		addresses.add(address);
		when(peopleRepo.save(peopleObject)).thenReturn(peopleObject);
		peopleService.updatePeopleAddress(peopleObject);
		verify(peopleRepo,atLeastOnce()).save(peopleObject);
	}

}
