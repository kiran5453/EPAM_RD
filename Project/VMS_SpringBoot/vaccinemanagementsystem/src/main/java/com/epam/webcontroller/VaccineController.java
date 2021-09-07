package com.epam.webcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dataobjects.Vaccine;
import com.epam.dto.VaccineDTO;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;

@Controller
public class VaccineController {
	
	@Autowired
	PeopleService peopleService;
	
	@Autowired
	VaccineService vaccineService;
	
	@RequestMapping("addVaccine")
	public String addVaccine() {
		return "addvaccine";
	}
	
	@RequestMapping("addStock")
	public String addStock() {
		return "addstock";
	}
	

	@RequestMapping("getAllVaccines")
	public ModelAndView displayAllVaccines() {
		ModelAndView mv=new ModelAndView();
		List<Vaccine> vaccineList=vaccineService.getVaccineDataFromDatabase();
		if(vaccineList.isEmpty()) {
			mv.setViewName("success");
			mv.addObject("message","Vaccines Not Available");
		}
		else {
			mv.setViewName("allvaccinedisplay");
			mv.addObject("vaccines",vaccineList);
		}
		return mv;
	}
	
	@RequestMapping("vaccine")
	public ModelAndView saveVaccine(VaccineDTO vaccineDto) {
		Vaccine vaccine=new Vaccine();
		vaccine.setVaccineName(vaccineDto.getVaccineName());
		vaccine.setInitialVaccineCount(vaccineDto.getBalanceVaccineCount());
		vaccine.setBalanceVaccineCount(vaccineDto.getBalanceVaccineCount());
		vaccine.setDateOfVaccineArrival(LocalDate.parse(vaccineDto.getDateOfVaccineArrival()));
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("success");
		if(vaccineService.findVaccineInDatabase(vaccine.getVaccineName())!=null)
			vaccineService.addStock(vaccine.getVaccineName(), vaccine.getBalanceVaccineCount());
		else
			vaccineService.insertVaccineDataToDatabase(vaccine.getVaccineName(), vaccine);
		mv.addObject("message","vaccine stock updated successfully");
		return mv;
	}
	
	@RequestMapping("stock")
	public ModelAndView addStock(String name,long amount) {
		ModelAndView mv=new ModelAndView();
		if(vaccineService.findVaccineInDatabase(name)!=null) {
			vaccineService.addStock(name,amount);
		}
		else {
			Vaccine vaccine=new Vaccine();
			vaccine.setVaccineName(name);
			vaccine.setInitialVaccineCount(amount);
			vaccine.setBalanceVaccineCount(amount);
			vaccine.setDateOfVaccineArrival(LocalDate.now());
			vaccineService.insertVaccineDataToDatabase(name, vaccine);
		}
		mv.setViewName("success");
		mv.addObject("message","vaccine stock updated successfully");
		return mv;
	}
	
}
