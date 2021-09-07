package com.epam.databasetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.MySQLDB;
import com.epam.dataobjects.People;

@ExtendWith(MockitoExtension.class)
class FindPeopleTest {

	@Mock
	EntityManager eManager;
	@InjectMocks
	MySQLDB databaseObject;
	People peopleObject;
	
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
	void findPeopleTestWithPerson() {
		when(eManager.find(People.class,"kiran")).thenReturn(peopleObject);
		assertEquals(peopleObject,databaseObject.findPeople("kiran"));
		verify(eManager,atLeastOnce()).find(People.class,"kiran");
	}
	
	@Test
	void findPeopleTestWithoutPerson() {
		when(eManager.find(People.class,"sai")).thenReturn(null);
		assertEquals(null,databaseObject.findPeople("sai"));
		verify(eManager,atLeastOnce()).find(People.class,"sai");
	}


}
