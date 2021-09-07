package com.epam.dtotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.VaccineDTO;

@ExtendWith(MockitoExtension.class)
class VaccineDTOTest {
	@InjectMocks
	VaccineDTO vaccineDto;
	String expectedString="vaccineName=covaxin, stockReceivedDate=15-06-2021, currentQuantity=10";
	
	@BeforeEach
	void setup() {
		vaccineDto.setInitialVaccineCount(10);
		vaccineDto.setBalanceVaccineCount(10);
		vaccineDto.setDateOfVaccineArrival("2021-07-10");
		vaccineDto.setVaccineName("covaxin");
	}
	@Test
	void getVaccineNameTest() {
		assertEquals("covaxin",vaccineDto.getVaccineName());
	}
	@Test
	void getStockReceivedDateTest() {
		assertEquals("2021-07-10",vaccineDto.getDateOfVaccineArrival());
	}
	@Test
	void getbalanceQuantityTest() {
		assertEquals(10,vaccineDto.getBalanceVaccineCount());
	}
	@Test
	void getInitialQuantityTest() {
		assertEquals(10,vaccineDto.getInitialVaccineCount());
	}

}
