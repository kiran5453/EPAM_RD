package com.epam.vaccineservicetest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
class AddStockTest {

	private Vaccine vaccineObject;
	@Mock
	VaccineRepository vaccineRepo;
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
	void addStockTestWithPositiveAmount() {
		when(vaccineRepo.findByVaccineName("covaxin")).thenReturn(vaccineObject);
		vaccineServiceObject.addStock("covaxin", 5);
		verify(vaccineRepo,times(1)).save(vaccineObject);
	}
	
	@Test
	void addStockTestWithNegativeAmount() {
		vaccineServiceObject.addStock("covaxin", -5);;
		verify(vaccineRepo,times(0)).save(vaccineObject);
	}
	
	@Test
	void addStockTestWithNoAmount() {
		vaccineServiceObject.addStock("covaxin", 0);
		verify(vaccineRepo,times(0)).save(vaccineObject);
	}

}
