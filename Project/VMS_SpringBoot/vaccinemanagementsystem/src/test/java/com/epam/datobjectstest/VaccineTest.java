package com.epam.datobjectstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dataobjects.Vaccine;

@ExtendWith(MockitoExtension.class)
class VaccineTest {
	@InjectMocks
	Vaccine vaccine;
	String expectedString="vaccineName=covaxin, stockReceivedDate=15-06-2021, currentQuantity=10";
	
	@BeforeEach
	void setup() {
		vaccine.setInitialVaccineCount(10);
		vaccine.setBalanceVaccineCount(10);
		vaccine.setDateOfVaccineArrival(LocalDate.parse("2021-07-10"));
		vaccine.setVaccineName("covaxin");
	}
	@Test
	void getVaccineNameTest() {
		assertEquals("covaxin",vaccine.getVaccineName());
	}
	@Test
	void getStockReceivedDateTest() {
		assertEquals(LocalDate.parse("2021-07-10"),vaccine.getDateOfVaccineArrival());
	}
	@Test
	void getbalanceQuantityTest() {
		assertEquals(10,vaccine.getBalanceVaccineCount());
	}
	@Test
	void getInitialQuantityTest() {
		assertEquals(10,vaccine.getInitialVaccineCount());
	}

}
