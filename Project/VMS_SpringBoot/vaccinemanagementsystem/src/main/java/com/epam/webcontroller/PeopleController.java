package com.epam.webcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dataobjects.People;
import com.epam.dto.PeopleDTO;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;

@Controller
public class PeopleController {
	
	@Autowired
	PeopleService peopleService;
	
	@Autowired
	VaccineService vaccineService;
	
	@RequestMapping("addPerson")
	public String addPerson() {
		return "personregister";
	}
	
	@RequestMapping("getVaccinated")
	public String inputAadhar() {
		return "inputaadhar";
	}
	
	@RequestMapping("register")
	public ModelAndView savePerson(PeopleDTO peopleDto) {
		ModelAndView mv = new ModelAndView();
		People person = new People();
		person.setAadharNumber(peopleDto.getAadharNumber());
		person.setAge(peopleDto.getAge());
		person.setFirstDoseDate(LocalDate.parse(peopleDto.getFirstDoseDate()));
		person.setName(peopleDto.getName());
		person.setVaccineType(peopleDto.getVaccineType());
		if(peopleService.findPeopleInDatabase(person.getAadharNumber())==null) {
				peopleService.insertPeopleDataToDatabase(person.getAadharNumber(), person);
				mv.setViewName("success");
				mv.addObject("message","Person Registration Successful");
		}
		else {
			mv.setViewName("success");
			mv.addObject("message","Person already exists");
		}
		return mv;
	}
	
	@RequestMapping("inputaadhar")
	public ModelAndView getVaccinated(String aadharNumber,String name) {
		ModelAndView mv = new ModelAndView();
			People person=peopleService.findPeopleInDatabase(aadharNumber);
			if(person!=null) {
				String output = peopleService.getVaccinated(person,name);
				mv.setViewName("success");
				mv.addObject("message",output);
		}
			else{
			mv.setViewName("noaadhar");
			mv.addObject("message","Person need to be registered first inorder to get vaccinated");
		}
		return mv;
	}
	

	@RequestMapping("getAllPersons")
	public ModelAndView displayAllPeople() {
		ModelAndView mv=new ModelAndView();
		List<People> personList=peopleService.getPeopleDataFromDatabase();
		if(personList.isEmpty()) {
			mv.setViewName("success");
			mv.addObject("message","People Not Available");
		}
		else {
		mv.setViewName("allpeopledisplay");
		mv.addObject("persons",personList);
		}
		return mv;
	}
}
