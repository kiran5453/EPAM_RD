package com.epam.databasetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.MySQLDB;
import com.epam.dataobjects.Vaccine;

@ExtendWith(MockitoExtension.class)
class FindVaccineTest {

	@Mock
	EntityManager eManager;
	@InjectMocks
	MySQLDB databaseObject;
	Vaccine vaccineObject;
	
	@BeforeEach
	void setup() {
		vaccineObject = new Vaccine();
		vaccineObject.setVaccinename("covaxin");
		vaccineObject.setDateofvaccinearrival(LocalDate.now());
		vaccineObject.setInitialvaccinecount(10);
		vaccineObject.setBalancevaccinecount(10);
	}
	
	@Test
	void findVaccineTestWithVaccine() {
		when(eManager.find(Vaccine.class,"covaxin")).thenReturn(vaccineObject);
		assertEquals(vaccineObject,databaseObject.findVaccine("covaxin"));
		verify(eManager,atLeastOnce()).find(Vaccine.class,"covaxin");
	}
	
	@Test
	void findVaccineTestWithoutVaccine() {
		when(eManager.find(Vaccine.class,"covishield")).thenReturn(null);
		assertEquals(null,databaseObject.findVaccine("covishield"));
		verify(eManager,atLeastOnce()).find(Vaccine.class,"covishield");
	}

}
