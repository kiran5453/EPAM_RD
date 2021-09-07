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
class IssueSecondDoseTest {

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
	void issueSecondDoseWithStockWithGapTest() {
		peopleObject.setFirstdosedate(LocalDate.parse("2021-06-01"));
		peopleObject.setVaccinetype("covaxin");
		when(vaccineServiceObject.vaccineStockAvailable("covaxin")).thenReturn(true);
		String result  = peopleServiceObject.issueSecondDose(peopleObject);
		verify(vaccineServiceObject,atLeastOnce()).decrementVaccineCount("covaxin");
		assertEquals("\nSuccessfully completed 2 doses of vaccine...\nStay Safe\n",result);
	}
	
	@Test
	void issueSecondDoseWithStockWithoutGapTest() {
		peopleObject.setFirstdosedate(LocalDate.now());
		peopleObject.setVaccinetype("covaxin");
		long duration = peopleServiceObject.findDuration(peopleObject.getFirstdosedate(),LocalDate.now());
		String result  = peopleServiceObject.issueSecondDose(peopleObject);
		assertEquals("You should have a gap of 21 days between two doses\nPlease visit after "
				+ (21 - duration) + " days\n",result);
	}
	
	@Test
	void issueSecondDoseWithoutStockWithGapTest() {
		peopleObject.setFirstdosedate(LocalDate.parse("2021-06-01"));
		peopleObject.setVaccinetype("covaxin");
		when(vaccineServiceObject.vaccineStockAvailable("covaxin")).thenReturn(false);
		String result  = peopleServiceObject.issueSecondDose(peopleObject);
		assertEquals("<-- Vaccine out of Stock -->\n",result);
	}
	

}
