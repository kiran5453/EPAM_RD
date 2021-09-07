package com.epam.vaccineservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
class VaccineStockTest {

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
	void vaccineStockAvailableTestWithStock() {
		vaccineObject.setBalanceVaccineCount(5);
		when(vaccineRepo.findByVaccineName("covaxin")).thenReturn(vaccineObject);
		assertEquals(true,vaccineServiceObject.vaccineStockAvailable("covaxin"));
	}
	
	@Test
	void vaccineStockAvailableTestWithoutStock() {
		vaccineObject.setBalanceVaccineCount(0);
		when(vaccineRepo.findByVaccineName("covaxin")).thenReturn(vaccineObject);
		assertEquals(false,vaccineServiceObject.vaccineStockAvailable("covaxin"));
	}
	
	@Test
	void vaccineStockAvailableTestWithoutVaccine() {
		when(vaccineRepo.findByVaccineName("covishield")).thenReturn(null);
		assertEquals(false,vaccineServiceObject.vaccineStockAvailable("covishield"));
	}

}
