package com.epam.peopleservicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
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

import com.epam.database.MySQLDB;
import com.epam.dataobjects.Address;
import com.epam.dataobjects.People;
import com.epam.service.PeopleService;

@ExtendWith(MockitoExtension.class)
class PeopleFromDatabaseTest {

	private People peopleObject;
	@Mock
	private MySQLDB databaseObject;
	@InjectMocks
	private PeopleService peopleService;
	
	@BeforeEach
	void setup() {
	
		peopleObject = new People();
		peopleObject.setName("kiran");
		peopleObject.setAadharnumber("972599404967");
		peopleObject.setAge(20);
		peopleObject.setFirstdosedate(null);
		peopleObject.setSeconddosedate(null);
		peopleObject.setVaccinetype(null);
	}
	
	@Test
	void insertPeopleToDatabaseTest() {
		
		doNothing().when(databaseObject).addPeopledata("972599404967", peopleObject);
		peopleService.insertPeopleDataToDatabase("972599404967", peopleObject);
		verify(databaseObject,atLeastOnce()).addPeopledata("972599404967",peopleObject);
	}
	
	@Test
	void getPeopleDataFromDatabaseTest() {
		List<People> peopleList = new ArrayList<People>();
		peopleList.add(peopleObject);
		when(databaseObject.getPeopledata()).thenReturn(peopleList);
		assertEquals(peopleObject,peopleService.getPeopleDataFromDatabase().get(0));
	}

	@Test
	void findPeopleInDatabaseWithPersonTest() {
		when(databaseObject.findPeople("972599404967")).thenReturn(peopleObject);
		assertEquals(peopleObject,peopleService.findPeopleInDatabase("972599404967"));
	}
	
	@Test
	void findPeopleInDatabaseWithoutPersonTest() {
		when(databaseObject.findPeople("982599404967")).thenReturn(null);
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
		doNothing().when(databaseObject).addAddress(peopleObject,addresses);
		peopleService.insertAddressToDatabase(peopleObject,addresses);
		verify(databaseObject,atLeastOnce()).addAddress(peopleObject,addresses);
	}

}
