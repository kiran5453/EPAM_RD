package com.epam.restcontroller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dataobjects.Vaccine;
import com.epam.dto.VaccineDTO;
import com.epam.service.VaccineService;

@RestController
public class VaccineRestController {
	
	@Autowired
	VaccineService vaccineService;
	
	@GetMapping(value="vaccines")
	public ResponseEntity<List<Vaccine>> displayAllVaccines() {
		return new ResponseEntity<>(vaccineService.getVaccineDataFromDatabase(),HttpStatus.OK);
	}

	
	@PostMapping(value="addingvaccine")
	public ResponseEntity<Map<String,String>> saveVaccine(@RequestBody VaccineDTO vaccineDto){
		Vaccine vaccine=new Vaccine();
		vaccine.setVaccineName(vaccineDto.getVaccineName());
		vaccine.setInitialVaccineCount(vaccineDto.getBalanceVaccineCount());
		vaccine.setBalanceVaccineCount(vaccineDto.getBalanceVaccineCount());
		vaccine.setDateOfVaccineArrival(LocalDate.parse(vaccineDto.getDateOfVaccineArrival()));
		if(vaccineService.findVaccineInDatabase(vaccine.getVaccineName())!=null)
			vaccineService.addStock(vaccine.getVaccineName(), vaccine.getBalanceVaccineCount());
		else
			vaccineService.insertVaccineDataToDatabase(vaccine.getVaccineName(), vaccine);
		Map<String, String> map = new HashMap<>();
		map.put("message","vaccine stock updated successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("updatingStock")
	public ResponseEntity<Map<String,String>> addStock(@RequestBody Map<String,Integer> input) {
		Map<String, String> map = new HashMap<>();
		String name="";
		int amount=0;
		for(String k:input.keySet()) {
			name = k;
			amount=input.get(k);
			break;
		};
		Vaccine vaccine = vaccineService.findVaccineInDatabase(name);
		if(vaccine!=null) {
			vaccineService.addStock(name,amount);
		}
		else {
			vaccineService.insertVaccineDataToDatabase(name, vaccine);
		}
		map.put("message","vaccine stock updated successfully");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
