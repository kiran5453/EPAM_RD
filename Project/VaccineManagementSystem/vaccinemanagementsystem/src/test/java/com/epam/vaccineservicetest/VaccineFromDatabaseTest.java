package com.epam.vaccineservicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
class VaccineFromDatabaseTest {

	private Vaccine vaccineObject;
	@Mock
	private Database databaseObject;
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
	void insertVaccineToDatabaseTest() {
		
		doNothing().when(databaseObject).addVaccineData("covaxin", vaccineObject);
		vaccineServiceObject.insertVaccineDataToDatabase("covaxin", vaccineObject);
		verify(databaseObject,atLeastOnce()).addVaccineData("covaxin",vaccineObject);
	}
	
	@Test
	void getVaccineDataFromDatabaseTest() {
		List<Vaccine> vaccineList = new ArrayList<Vaccine>();
		vaccineList.add(vaccineObject);
		when(databaseObject.getVaccinedata()).thenReturn(vaccineList);
		assertEquals(vaccineObject,vaccineServiceObject.getVaccineDataFromDatabase().get(0));
	}

	@Test
	void findPeopleInDatabaseWithPersonTest() {
		when(databaseObject.findVaccine("covaxin")).thenReturn(vaccineObject);
		assertEquals(vaccineObject,vaccineServiceObject.findVaccineInDatabase("covaxin"));
	}
	
	@Test
	void findPeopleInDatabaseWithoutPersonTest() {
		when(databaseObject.findVaccine("covishield")).thenReturn(null);
		assertEquals(null,vaccineServiceObject.findVaccineInDatabase("covishield"));
	}
}
