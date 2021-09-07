package com.epam.databasetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.MySQLDB;
import com.epam.dataobjects.People;

@ExtendWith(MockitoExtension.class)
class GetPeopleDataTest {
	
	private People peopleObject;
	@InjectMocks
	private MySQLDB databaseObject;
	@Mock
	EntityManager eManager;
	@Mock
	TypedQuery<People> query;
	
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
	void getPeopleDataTest() {
		List<People> peopleList = new ArrayList<>();
		peopleList.add(peopleObject);
		when(eManager.createQuery("select p from People p", People.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(peopleList);
		assertEquals(peopleList,databaseObject.getPeopledata());
	}

}
