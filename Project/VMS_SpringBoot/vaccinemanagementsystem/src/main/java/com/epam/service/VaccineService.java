package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dataobjects.Vaccine;
import com.epam.repositories.VaccineRepository;

@Service
public class VaccineService {

	
	@Autowired
	VaccineRepository vaccineRepo;

	public void addStock(String name, long amount) {
		if (amount > 0) {
			Vaccine vaccine = vaccineRepo.findByVaccineName(name);
			vaccine.setBalanceVaccineCount(vaccine.getBalanceVaccineCount()+amount);
			vaccine.setInitialVaccineCount(vaccine.getInitialVaccineCount()+amount);
			vaccineRepo.save(vaccine);
		}
	}

	public void decrementVaccineCount(String name) {
		Vaccine vaccine = vaccineRepo.findByVaccineName(name);
		vaccine.setBalanceVaccineCount(vaccine.getBalanceVaccineCount()-1);
		vaccineRepo.save(vaccine);
	}

	public boolean vaccineStockAvailable(String name) {
		boolean isavailable = false;
		Vaccine vaccine = vaccineRepo.findByVaccineName(name);
		if (vaccine != null && vaccine.getBalanceVaccineCount() > 0)
			isavailable = true;
		return isavailable;
	}
	
	public void insertVaccineDataToDatabase(String name,Vaccine vaccine) {
		vaccineRepo.save(vaccine);
	}
	
	public List<Vaccine> getVaccineDataFromDatabase() {
		return (List<Vaccine>) vaccineRepo.findAll();
	}
	
	public Vaccine findVaccineInDatabase(String name) {
		return vaccineRepo.findByVaccineName(name);
	}
}
