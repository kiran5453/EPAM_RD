package com.epam.databasetest;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.MySQLDB;
import com.epam.dataobjects.Vaccine;

@ExtendWith(MockitoExtension.class)
class AddVaccineDataTest {

	private Vaccine vaccineObject;
	@InjectMocks
	private MySQLDB databaseObject;
	@Mock
	EntityManager eManager;
	@Mock
	EntityTransaction transaction;
	
	@BeforeEach
	void setup() {
		vaccineObject = new Vaccine();
		vaccineObject.setVaccinename("covaxin");
		vaccineObject.setDateofvaccinearrival(LocalDate.now());
		vaccineObject.setInitialvaccinecount(10);
		vaccineObject.setBalancevaccinecount(10);
	}
	
	@Test
	void addVaccineDataTest() {
		when(eManager.getTransaction()).thenReturn(transaction);
		doNothing().when(eManager).persist(vaccineObject);
		databaseObject.addVaccineData("covaxin",vaccineObject);
		verify(eManager,atLeastOnce()).persist(vaccineObject);
	}

}
