package com.epam.databasetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.epam.dataobjects.Vaccine;

@ExtendWith(MockitoExtension.class)
class GetVaccineDataTest {

	private Vaccine vaccineObject;
	@InjectMocks
	private MySQLDB databaseObject;
	@Mock
	EntityManager eManager;
	@Mock
	TypedQuery<Vaccine> query;
	
	@BeforeEach
	void setup() {
	
		vaccineObject = new Vaccine();
		vaccineObject.setVaccinename("covaxin");
		vaccineObject.setDateofvaccinearrival(LocalDate.now());
		vaccineObject.setInitialvaccinecount(10);
		vaccineObject.setBalancevaccinecount(10);
	}

	@Test
	void getPeopleDataTest() {
		List<Vaccine> vaccineList = new ArrayList<>();
		vaccineList.add(vaccineObject);
		when(eManager.createQuery("select v from Vaccine v", Vaccine.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(vaccineList);
		assertEquals(vaccineList,databaseObject.getVaccinedata());
	}

}
