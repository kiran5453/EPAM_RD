package com.epam.databasetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.MySQLDB;
import com.epam.dataobjects.Address;
import com.epam.dataobjects.People;

@ExtendWith(MockitoExtension.class)
class AddPeopleDataTest {

	private People peopleObject;
	@InjectMocks
	private MySQLDB databaseObject;
	@Mock
	EntityManager eManager;
	@Mock
	EntityTransaction transaction;
	
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
	void addPeopleDataTest() {
		when(eManager.getTransaction()).thenReturn(transaction);
		doNothing().when(eManager).persist(peopleObject);
		databaseObject.addPeopledata("972599404967",peopleObject);
		verify(eManager,atLeastOnce()).persist(peopleObject);
	}
	
	@Test
	void addAddressTest() {
		when(eManager.getTransaction()).thenReturn(transaction);
		List<Address> addresses = new ArrayList<>();
		Address address = new Address();
		address.setCity("gnt");
		address.setStreet("gdpd");
		address.setState("ap");
		address.setPincode(522403);
		address.setType("home");
		address.setPeople(peopleObject);
		addresses.add(address);
		databaseObject.addAddress(peopleObject, addresses);
		assertEquals(address,peopleObject.getAddresses().get(0));
	}

}
