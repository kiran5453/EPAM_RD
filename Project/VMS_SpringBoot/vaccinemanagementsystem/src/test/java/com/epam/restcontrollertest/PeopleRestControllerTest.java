package com.epam.restcontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epam.dataobjects.People;
import com.epam.dto.PeopleDTO;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;
import com.epam.webcontroller.PeopleController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PeopleRestControllerTest {

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
		peopleDto.setSecondDoseDate("2021-08-10");
		peopleDto.setVaccineType("covaxin");
		people.setAadharNumber(peopleDto.getAadharNumber());
		people.setName(peopleDto.getName());
		people.setAge(peopleDto.getAge());
		people.setFirstDoseDate(LocalDate.parse(peopleDto.getFirstDoseDate()));
		people.setSecondDoseDate(LocalDate.parse(peopleDto.getSecondDoseDate()));
		people.setVaccineType(peopleDto.getVaccineType());
	}
	
	@Test
	void contextLoads() throws Exception {
	assertThat(controller).isNotNull();
	}
	
	@Test
	void displayAllPeopleTestWithPeople() throws Exception{
		List<People> peopleList = new ArrayList<>();
		peopleList.add(people);
		when(peopleService.getPeopleDataFromDatabase()).thenReturn(peopleList);
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/peoples")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("kiran")));
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);

}
	
	@Test
	void savePersonWithPerson() throws Exception{
		when(peopleService.findPeopleInDatabase("972599404967")).thenReturn(people);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/registering").content(asJsonString(peopleDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Person already exists"));
	}
	
	@Test
	void savePersonWithoutPerson() throws Exception{
		when(peopleService.findPeopleInDatabase("982599404967")).thenReturn(null);
		peopleDto.setAadharNumber("982599404967");
		people.setAadharNumber("982599404967");
		doNothing().when(peopleService).insertPeopleDataToDatabase("982599404967", people);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/registering").content(asJsonString(peopleDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Person Registration Successful"));
	}
	
	@Test
	void getVaccinatedWithoutRegisterTest() throws Exception{
		Map<String,String> map = new HashMap<>();
		map.put("982599404967","covaxin");
		when(peopleService.findPeopleInDatabase("982599404967")).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/getVaccination").content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Person need to be registered first inorder to get vaccinated"));
	}
	
	@Test
	void getVaccinatedWithRegisterTest() throws Exception{
		Map<String,String> map = new HashMap<>();
		map.put("972599404967","covaxin");
		when(peopleService.findPeopleInDatabase("972599404967")).thenReturn(people);
		when(peopleService.getVaccinated(people, "covaxin")).thenReturn("\nPerson had already completed 2 doses of vaccine...\nPerson can only take 2 doses of vaccine\nStay Safe\n");
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/getVaccination").content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("\nPerson had already completed 2 doses of vaccine...\nPerson can only take 2 doses of vaccine\nStay Safe\n"));
	}
	
	@Test
	void savePersonInvalidTest() throws Exception{
		when(peopleService.findPeopleInDatabase("92599404967")).thenReturn(null);
		peopleDto.setAadharNumber("92599404967");
		people.setAadharNumber("92599404967");
		doNothing().when(peopleService).insertPeopleDataToDatabase("92599404967", people);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/registering").content(asJsonString(peopleDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.details").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.details").value("aadhar shoud be 12 digits, "));
	}
	
}
