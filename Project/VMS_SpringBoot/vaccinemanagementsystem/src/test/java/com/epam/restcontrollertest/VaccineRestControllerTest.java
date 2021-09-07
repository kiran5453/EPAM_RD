package com.epam.restcontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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

import com.epam.dataobjects.Vaccine;
import com.epam.dto.VaccineDTO;
import com.epam.service.VaccineService;
import com.epam.webcontroller.VaccineController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class VaccineRestControllerTest {

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
	void displayAllPeopleTestWithPeople() throws Exception{
		List<Vaccine> vaccineList = new ArrayList<>();
		vaccineList.add(vaccine);
		when(vaccineService.getVaccineDataFromDatabase()).thenReturn(vaccineList);
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/vaccines")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].vaccineName", is("covaxin")));
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);

}

	@Test
	void saveVaccineTestWithVaccine() throws Exception{
		when(vaccineService.findVaccineInDatabase("covaxin")).thenReturn(vaccine);
		doNothing().when(vaccineService).addStock("covaxin", 10);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/addingvaccine").content(asJsonString(vaccineDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("vaccine stock updated successfully"));
	}

	@Test
	void saveVaccineTestWithoutVaccine() throws Exception{
		when(vaccineService.findVaccineInDatabase("covishield")).thenReturn(null);
		vaccineDto.setVaccineName("covishield");
		vaccine.setVaccineName("covishield");
		doNothing().when(vaccineService).insertVaccineDataToDatabase("covishield", vaccine);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/addingvaccine").content(asJsonString(vaccineDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("vaccine stock updated successfully"));
	}
	
	@Test
	void addStockTestWithoutVaccine() throws Exception{
		Map<String,Integer> map = new HashMap<>();
		map.put("covishield", 2);
		when(vaccineService.findVaccineInDatabase("covishield")).thenReturn(null);
		vaccineDto.setVaccineName("covishield");
		vaccine.setVaccineName("covishield");
		doNothing().when(vaccineService).insertVaccineDataToDatabase("covishield", vaccine);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/updatingStock").content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("vaccine stock updated successfully"));
	}
	
	@Test
	void addStockTestWithVaccine() throws Exception{
		Map<String,Integer> map = new HashMap<>();
		map.put("covaxin", 2);
		when(vaccineService.findVaccineInDatabase("covaxin")).thenReturn(vaccine);
		doNothing().when(vaccineService).addStock("covaxin", 1);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/updatingStock").content(asJsonString(map))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("vaccine stock updated successfully"));
	}
}
