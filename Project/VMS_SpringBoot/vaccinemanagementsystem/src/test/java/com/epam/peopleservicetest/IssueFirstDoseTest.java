package com.epam.peopleservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.epam.repositories.PeopleRepository;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;

@ExtendWith(MockitoExtension.class)
class IssueFirstDoseTest {

	private People peopleObject;
	@InjectMocks
	PeopleService peopleServiceObject;
	@Mock
	VaccineService vaccineServiceObject;
	@Mock
	PeopleRepository peopleRepo;
	
	@BeforeEach
	void setup() {
	
		peopleObject = new People();
		peopleObject.setName("kiran");
		peopleObject.setAadharNumber("972599404967");
		peopleObject.setAge(20);
		peopleObject.setFirstDoseDate(null);
		peopleObject.setSecondDoseDate(null);
		peopleObject.setVaccineType(null);
	}
	
	@Test
	void issueFirstDoseWithVaccineWithStockTest() {
		when(vaccineServiceObject.vaccineStockAvailable("covaxin")).thenReturn(true);
		when(peopleRepo.save(peopleObject)).thenReturn(peopleObject);
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
