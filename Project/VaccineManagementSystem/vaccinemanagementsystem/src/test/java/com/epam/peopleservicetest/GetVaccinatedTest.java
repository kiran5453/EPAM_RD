package com.epam.peopleservicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dataobjects.People;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;

@ExtendWith(MockitoExtension.class)
class GetVaccinatedTest {

	private People peopleObject;
	@InjectMocks
	PeopleService peopleServiceObject;
	@Mock
	VaccineService vaccineServiceObject;
	
	@BeforeEach
	void setup() {
	
		peopleObject = new People();
		peopleObject.setAadharnumber("972599404967");
		peopleObject.setAge(20);
		peopleObject.setFirstdosedate(null);
		peopleObject.setSeconddosedate(null);
		peopleObject.setVaccinetype(null);
	}
	
	@Test
	void getVaccinatedWithFirstDoseNullTest() {
		when(vaccineServiceObject.vaccineStockAvailable("covaxin")).thenReturn(true);
		String result  = peopleServiceObject.getVaccinated(peopleObject,"covaxin");
		verify(vaccineServiceObject,atLeastOnce()).decrementVaccineCount("covaxin");
		assertEquals("\nReceived first dose of vaccine...\nPlease visit after 21 days for the second dose\n\n",result);
	}
	
	@Test
	void getVaccinatedWithFirstDoseNotNullSecondDoseNullTest() {
		peopleObject.setFirstdosedate(LocalDate.parse("2021-06-01"));
		peopleObject.setVaccinetype("covaxin");
		when(vaccineServiceObject.vaccineStockAvailable("covaxin")).thenReturn(true);
		String result  = peopleServiceObject.getVaccinated(peopleObject,"covaxin");
		verify(vaccineServiceObject,atLeastOnce()).decrementVaccineCount("covaxin");
		assertEquals("\nSuccessfully completed 2 doses of vaccine...\nStay Safe\n",result);
	}
	
	@Test
	void getVaccinatedWithFirstDoseNotNullSecondDoseNotNullTest() {
		peopleObject.setFirstdosedate(LocalDate.parse("2021-06-01"));
		peopleObject.setSeconddosedate(LocalDate.parse("2021-06-25"));
		String result  = peopleServiceObject.getVaccinated(peopleObject,"covaxin");
		assertEquals("\nPerson had already completed 2 doses of vaccine...\nPerson can only take 2 doses of vaccine\nStay Safe\n",result);
	}

}
