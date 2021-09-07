package com.epam.peopleservicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dataobjects.People;
import com.epam.service.MainService;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;

@ExtendWith(MockitoExtension.class)
class IssueFirstDoseTest {

	private People peopleObject;
	@InjectMocks
	PeopleService peopleServiceObject;
	@Mock
	VaccineService vaccineServiceObject;
	MainService serviceObject = new MainService();
	
	@BeforeEach
	void setup() {
	
		peopleObject = new People();
		peopleObject.setName("kiran");
		peopleObject.setAadharnumber("972599404967");
		peopleObject.setAge(20);
		peopleObject.setFirstdosedate(null);
		peopleObject.setSeconddosedate(null);
		peopleObject.setVaccinetype(null);
	}
	
	@Test
	void issueFirstDoseWithVaccineWithStockTest() {
		when(vaccineServiceObject.vaccineStockAvailable("covaxin")).thenReturn(true);
		String result  = peopleServiceObject.issueFirstDose(peopleObject,"covaxin");
		verify(vaccineServiceObject,atLeastOnce()).decrementVaccineCount("covaxin");
		assertEquals("\nReceived first dose of vaccine...\nPlease visit after 21 days for the second dose\n\n",result);
	}
	
	@Test
	void issueFirstDoseWithVaccineWithNoStockTest() {
		when(vaccineServiceObject.vaccineStockAvailable("covaxin")).thenReturn(false);
		String result  = peopleServiceObject.issueFirstDose(peopleObject,"covaxin");
		assertEquals("<-- Vaccine out of Stock -->",result);
	}
	
	@Test
	void issueFirstDoseWithoutVaccineTest() {
		when(vaccineServiceObject.vaccineStockAvailable("covishield")).thenReturn(false);
		String result  = peopleServiceObject.issueFirstDose(peopleObject,"covishield");
		assertEquals("<-- Vaccine out of Stock -->",result);
	}

}
