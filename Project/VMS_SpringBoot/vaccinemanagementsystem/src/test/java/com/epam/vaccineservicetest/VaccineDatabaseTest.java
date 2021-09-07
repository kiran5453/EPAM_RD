package com.epam.vaccineservicetest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
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

import com.epam.dataobjects.Vaccine;
import com.epam.repositories.VaccineRepository;
import com.epam.service.VaccineService;

@ExtendWith(MockitoExtension.class)
class VaccineDatabaseTest {

	private Vaccine vaccineObject;
	@Mock
	private VaccineRepository vaccineRepo;
	@InjectMocks
	VaccineService vaccineServiceObject;
	
	@BeforeEach
	void setup() {
		vaccineObject = new Vaccine();
		vaccineObject.setVaccineName("covaxin");
		vaccineObject.setDateOfVaccineArrival(LocalDate.now());
		vaccineObject.setInitialVaccineCount(10);
		vaccineObject.setBalanceVaccineCount(10);
	}
	
	@Test
	void insertVaccineToDatabaseTest() {
		
		when(vaccineRepo.save(vaccineObject)).thenReturn(vaccineObject);
		vaccineServiceObject.insertVaccineDataToDatabase("covaxin", vaccineObject);
		verify(vaccineRepo,times(1)).save(vaccineObject);
	}
	
	@Test
	void getVaccineDataFromDatabaseTest() {
		List<Vaccine> vaccineList = new ArrayList<Vaccine>();
		vaccineList.add(vaccineObject);
		when(vaccineRepo.findAll()).thenReturn(vaccineList);
		assertEquals(vaccineObject,vaccineServiceObject.getVaccineDataFromDatabase().get(0));
	}

	@Test
	void findPeopleInDatabaseWithPersonTest() {
		when(vaccineRepo.findByVaccineName("covaxin")).thenReturn(vaccineObject);
		assertEquals(vaccineObject,vaccineServiceObject.findVaccineInDatabase("covaxin"));
	}
	
	@Test
	void findPeopleInDatabaseWithoutPersonTest() {
		when(vaccineRepo.findByVaccineName("covishield")).thenReturn(null);
		assertEquals(null,vaccineServiceObject.findVaccineInDatabase("covishield"));
	}
}
