package com.epam.mvctest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.webcontroller.PeopleController;

@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PeopleController controller;
	
	@Test
	void contextLoads() throws Exception {
	assertThat(controller).isNotNull();
	}
	
	@Test
    void homePageTest() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
	
	@Test
    void peopleMenuPageTest() throws Exception {
        this.mockMvc.perform(get("/managePeople")).andExpect(view().name("peoplemenu"));
    }

	@Test
    void vaccineMenuPageTest() throws Exception {
        this.mockMvc.perform(get("/manageVaccine")).andExpect(view().name("vaccinemenu"));
    }
	
	@Test
    void returnToMenuPageTest() throws Exception {
        this.mockMvc.perform(get("/returnToMenu")).andExpect(view().name("home"));
    }
}
