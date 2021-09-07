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

import com.epam.dataobjects.People;
import com.epam.dto.PeopleDTO;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;
import com.epam.webcontroller.PeopleController;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class PeopleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PeopleController controller;
	
	@MockBean
	PeopleService peopleService;
	@MockBean
	VaccineService vaccineService;
	
	
	People people = new People();
	PeopleDTO peopleDto = new PeopleDTO();
	
	@BeforeEach
	void setup() {
		
		peopleDto.setAadharNumber("972599404967");
		peopleDto.setName("kiran");
		peopleDto.setAge(21);
		peopleDto.setFirstDoseDate("2021-07-10");
		peopleDto.setSecondDoseDate(null);
		peopleDto.setVaccineType("covaxin");
		people.setAadharNumber(peopleDto.getAadharNumber());
		people.setName(peopleDto.getName());
		people.setAge(peopleDto.getAge());
		people.setFirstDoseDate(LocalDate.parse(peopleDto.getFirstDoseDate()));
		people.setSecondDoseDate(null);
		people.setVaccineType(peopleDto.getVaccineType());
	}
	
	@Test
	void contextLoads() throws Exception {
	assertThat(controller).isNotNull();
	}
	
	@Test
	void savePersonTestWithoutPerson() throws Exception {
		when(peopleService.findPeopleInDatabase("982599404967")).thenReturn(null);
		doNothing().when(peopleService).insertPeopleDataToDatabase("982599404967", people);
	    this.mockMvc.perform(MockMvcRequestBuilderUtils.postForm("http://localhost:8081/register",peopleDto)).andExpect(model().attribute("message","Person Registration Successful"));

	}
	@Test
	void savePersonTestWithPerson() throws Exception {
		when(peopleService.findPeopleInDatabase("972599404967")).thenReturn(people);
	    this.mockMvc.perform(MockMvcRequestBuilderUtils.postForm("http://localhost:8081/register",peopleDto))
	    .andExpect(model().attribute("message","Person already exists"));

	}
	

	@Test
	void getVaccinatedTestWithRegister() throws Exception{
		when(peopleService.findPeopleInDatabase("972599404967")).thenReturn(people);
		when(peopleService.getVaccinated(people, "covaxin")).thenReturn("\nSuccessfully completed 2 doses of vaccine...\nStay Safe\n");
		this.mockMvc.perform(post("http://localhost:8081/inputaadhar").param("aadharNumber", "972599404967")
				.param("name", "covaxin")).andExpect(model().attribute("message", "\nSuccessfully completed 2 doses of vaccine...\nStay Safe\n"));
	}
	
	@Test
	void getVaccinatedTestWithoutRegister() throws Exception{
		when(peopleService.findPeopleInDatabase("982599404967")).thenReturn(null);
		this.mockMvc.perform(post("http://localhost:8081/inputaadhar").param("aadharNumber", "982599404967")
				.param("name", "covaxin")).andExpect(model().attribute("message", "Person need to be registered first inorder to get vaccinated"));
	}
	
	@Test
	void displayAllPeopleTestWithPeople() throws Exception{
		List<People> peopleList = new ArrayList<>();
		peopleList.add(people);
		when(peopleService.getPeopleDataFromDatabase()).thenReturn(peopleList);
		this.mockMvc.perform(post("http://localhost:8081/getAllPersons"))
		.andExpect(model().attribute("persons",peopleList));
	}
	
	@Test
	void displayAllPeopleTestWithoutPeople() throws Exception{
		List<People> peopleList = new ArrayList<>();
		when(peopleService.getPeopleDataFromDatabase()).thenReturn(peopleList);
		this.mockMvc.perform(post("http://localhost:8081/getAllPersons"))
		.andExpect(model().attribute("message","People Not Available"));
	}
	
	@Test
    void personRegisterPageTest() throws Exception {
        this.mockMvc.perform(get("/addPerson")).andExpect(view().name("personregister"));
    }
	
	@Test
    void inputAadharPageTest() throws Exception {
        this.mockMvc.perform(get("/getVaccinated")).andExpect(view().name("inputaadhar"));
    }
}
