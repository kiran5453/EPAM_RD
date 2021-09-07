package com.epam.mvctest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dataobjects.Vaccine;
import com.epam.dto.VaccineDTO;
import com.epam.service.VaccineService;
import com.epam.webcontroller.VaccineController;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

@SpringBootTest
@AutoConfigureMockMvc
class VaccineControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private VaccineController controller;
	
	@MockBean
	VaccineService vaccineService;

	Vaccine vaccine;
	VaccineDTO vaccineDto;

	@BeforeEach
	void setup() {
		vaccineDto = new VaccineDTO();
		vaccineDto.setInitialVaccineCount(10);
		vaccineDto.setBalanceVaccineCount(10);
		vaccineDto.setDateOfVaccineArrival("2021-07-10");
		vaccineDto.setVaccineName("covaxin");
		vaccine = new Vaccine();
		vaccine.setVaccineName(vaccineDto.getVaccineName());
		vaccine.setDateOfVaccineArrival(LocalDate.parse(vaccineDto.getDateOfVaccineArrival()));
		vaccine.setInitialVaccineCount(vaccineDto.getInitialVaccineCount());
		vaccine.setBalanceVaccineCount(vaccineDto.getBalanceVaccineCount());
	}
	
	@Test
	void contextLoads() throws Exception {
	assertThat(controller).isNotNull();
	}

	@Test
	void displayAllVaccinesTestWithVaccine() throws Exception{
		List<Vaccine> vaccineList = new ArrayList<>();
		vaccineList.add(vaccine);
		when(vaccineService.getVaccineDataFromDatabase()).thenReturn(vaccineList);
		this.mockMvc.perform(post("http://localhost:8081/getAllVaccines"))
		.andExpect(model().attribute("vaccines",vaccineList));
	}
	
	@Test
	void displayAllPeopleTestWithoutPeople() throws Exception{
		List<Vaccine> vaccineList = new ArrayList<>();
		when(vaccineService.getVaccineDataFromDatabase()).thenReturn(vaccineList);
		this.mockMvc.perform(post("http://localhost:8081/getAllVaccines"))
		.andExpect(model().attribute("message","Vaccines Not Available"));
	}
	
	@Test
	void saveVaccineTestWithoutVaccine() throws Exception {
		when(vaccineService.findVaccineInDatabase("covishield")).thenReturn(null);
		doNothing().when(vaccineService).insertVaccineDataToDatabase("covishield",vaccine);
	    this.mockMvc.perform(MockMvcRequestBuilderUtils.postForm("http://localhost:8081/vaccine",vaccineDto))
	    .andExpect(model().attribute("message","vaccine stock updated successfully"));
	}
	
	@Test
	void saveVaccineTestWithVaccine() throws Exception {
		when(vaccineService.findVaccineInDatabase("covaxin")).thenReturn(vaccine);
		doNothing().when(vaccineService).addStock("covaxin", 10);
	    this.mockMvc.perform(MockMvcRequestBuilderUtils.postForm("http://localhost:8081/vaccine",vaccineDto))
	    .andExpect(model().attribute("message","vaccine stock updated successfully"));

	}
	
	@Test
	void addStockTestWithoutVaccine() throws Exception {
		when(vaccineService.findVaccineInDatabase("covishield")).thenReturn(null);
		doNothing().when(vaccineService).insertVaccineDataToDatabase("covishield",vaccine);
		this.mockMvc.perform(post("http://localhost:8081/stock")
				.param("name", "covishield").param("amount","1"))
	    .andExpect(model().attribute("message","vaccine stock updated successfully"));
	}
	
	@Test
	void addStockTestWithVaccine() throws Exception {
		when(vaccineService.findVaccineInDatabase("covaxin")).thenReturn(vaccine);
		doNothing().when(vaccineService).addStock("covaxin",1);
		this.mockMvc.perform(post("http://localhost:8081/stock")
				.param("name", "covaxin").param("amount", "1"))
	    .andExpect(model().attribute("message","vaccine stock updated successfully"));
	}
	
	@Test
    void addVaccinePageTest() throws Exception {
        this.mockMvc.perform(get("/addVaccine")).andExpect(view().name("addvaccine"));
    }
	
	@Test
    void addStockPageTest() throws Exception {
        this.mockMvc.perform(get("/addStock")).andExpect(view().name("addstock"));
    }
}
