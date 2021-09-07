package com.epam.vaccineservicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.Database;
import com.epam.dataobjects.Vaccine;
import com.epam.service.VaccineService;


@ExtendWith(MockitoExtension.class)
class VaccineStockTest {

	private Vaccine vaccineObject;
	@Mock
	private Database mockedDatabaseObject;
	@InjectMocks
	VaccineService vaccineServiceObject;
	
	@BeforeEach
	void setup() {
		vaccineObject = new Vaccine();
		vaccineObject.setVaccinename("covaxin");
		vaccineObject.setDateofvaccinearrival(LocalDate.now());
		vaccineObject.setInitialvaccinecount(10);
		vaccineObject.setBalancevaccinecount(10);
	}
	
	@Test
	void vaccineStockAvailableTestWithStock() {
		vaccineObject.setBalancevaccinecount(5);
		when(mockedDatabaseObject.findVaccine("covaxin")).thenReturn(vaccineObject);
		assertEquals(true,vaccineServiceObject.vaccineStockAvailable("covaxin"));
	}
	
	@Test
	void vaccineStockAvailableTestWithoutStock() {
		vaccineObject.setBalancevaccinecount(0);
		when(mockedDatabaseObject.findVaccine("covaxin")).thenReturn(vaccineObject);
		assertEquals(false,vaccineServiceObject.vaccineStockAvailable("covaxin"));
	}
	
	@Test
	void vaccineStockAvailableTestWithoutVaccine() {
		when(mockedDatabaseObject.findVaccine("covishield")).thenReturn(null);
		assertEquals(false,vaccineServiceObject.vaccineStockAvailable("covishield"));
	}

}
