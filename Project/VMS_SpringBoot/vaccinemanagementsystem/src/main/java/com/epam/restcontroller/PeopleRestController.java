package com.epam.restcontroller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dataobjects.People;
import com.epam.dto.PeopleDTO;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;

@RestController
public class PeopleRestController {
	
	@Autowired
	PeopleService peopleService;
	
	@Autowired
	VaccineService vaccineService;
	
	@GetMapping(value="peoples")
	public ResponseEntity<List<People>> displayAllVaccines() {
		return new ResponseEntity<>(peopleService.getPeopleDataFromDatabase(),HttpStatus.OK);
	}
	
	@PostMapping("registering")
	public ResponseEntity<Map<String,String>> savePerson(@RequestBody @Valid PeopleDTO peopleDto) throws Exception{
		Map<String, String> map = new HashMap<>();
		People person = new People();
		person.setAadharNumber(peopleDto.getAadharNumber());
		person.setAge(peopleDto.getAge());
		person.setFirstDoseDate(LocalDate.parse(peopleDto.getFirstDoseDate()));
		person.setName(peopleDto.getName());
		person.setVaccineType(peopleDto.getVaccineType());
		if(peopleService.findPeopleInDatabase(person.getAadharNumber())==null) {
			peopleService.insertPeopleDataToDatabase(person.getAadharNumber(), person);
			map.put("message","Person Registration Successful");
		}
		else {
			map.put("message","Person already exists");
		}
			return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@PostMapping("getVaccination")
	public ResponseEntity<Map<String,String>> getVaccinated(@RequestBody Map<String,String> input) {
		Map<String, String> map = new HashMap<>();
			String aadhar="";
			String name="";
			for(String k:input.keySet()) {
				aadhar = k;
				name=input.get(k);
				break;
			};
			People person=peopleService.findPeopleInDatabase(aadhar);
			if(person!=null) {
				String output = peopleService.getVaccinated(person, name);
			map.put("message",output);
		}
			else{
			map.put("message","Person need to be registered first inorder to get vaccinated");
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
