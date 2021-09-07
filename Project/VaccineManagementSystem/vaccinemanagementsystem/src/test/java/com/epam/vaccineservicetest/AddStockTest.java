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

import com.epam.database.Database;
import com.epam.dataobjects.Vaccine;
import com.epam.service.VaccineService;

@ExtendWith(MockitoExtension.class)
class AddStockTest {

	private Vaccine vaccineObject;
	@Mock
	private Database mockedDatabaseObject;
	@InjectMocks
	VaccineService vaccineServiceObject;
	
	@BeforeEach
	void setup() {
		vaccineObject = new Vaccine();
		vaccineObject.setVaccinename("covaxin");
		vaccineObject.setDateofvaccinearrival(LocalDate.now());
		vaccineObject.setInitialvaccinecount(10);
		vaccineObject.setBalancevaccinecount(10);
	}
	
	@Test
	void addStockTestWithPositiveAmount() {
		when(mockedDatabaseObject.findVaccine("covaxin")).thenReturn(vaccineObject);
		vaccineServiceObject.addStock("covaxin", 5);
		verify(mockedDatabaseObject,times(1)).increaseStock(vaccineObject,5);
	}
	
	@Test
	void addStockTestWithNegativeAmount() {
		vaccineServiceObject.addStock("covaxin", -5);;
		verify(mockedDatabaseObject,times(0)).increaseStock(vaccineObject,-5);
	}
	
	@Test
	void addStockTestWithNoAmount() {
		vaccineServiceObject.addStock("covaxin", 0);
		verify(mockedDatabaseObject,times(0)).increaseStock(vaccineObject,0);
	}

}
