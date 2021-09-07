package com.epam.peopleservicetest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.epam.service.PeopleService;

class FindDurationTest {

	@Test
	void findGapPositiveTest() {
		assertEquals(30,new PeopleService().findDuration(LocalDate.parse("2021-05-01"), LocalDate.parse("2021-05-31")));
	}
	
	@Test
	void findGapNegativeTest() {
		assertEquals(-30,new PeopleService().findDuration(LocalDate.parse("2021-05-31"), LocalDate.parse("2021-05-01")));
	}

	@Test
	void findNoGapTest() {
		assertEquals(0,new PeopleService().findDuration(LocalDate.parse("2021-05-01"), LocalDate.parse("2021-05-01")));
	}
}
