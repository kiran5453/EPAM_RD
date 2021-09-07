package com.epam.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.database.Database;
import com.epam.dataobjects.Vaccine;

@Component
public class VaccineService {

	private Database dbobject = MainService.getDBObject();

	public void addStock(String name, long amount) {
		if (amount > 0) {
			Vaccine vaccine = dbobject.findVaccine(name);
			dbobject.increaseStock(vaccine, amount);
		}
	}

	public void decrementVaccineCount(String name) {
		Vaccine vaccine = dbobject.findVaccine(name);
		dbobject.decrementBalanceStock(vaccine);
	}

	public boolean vaccineStockAvailable(String name) {
		boolean isavailable = false;
		Vaccine vaccine = dbobject.findVaccine(name);
		if (vaccine != null && vaccine.getBalancevaccinecount() > 0)
			isavailable = true;
		return isavailable;
	}
	
	public void insertVaccineDataToDatabase(String name,Vaccine vaccine) {
		dbobject.addVaccineData(name,vaccine);
	}
	
	public List<Vaccine> getVaccineDataFromDatabase() {
		return dbobject.getVaccinedata();
	}
	
	public Vaccine findVaccineInDatabase(String name) {
		return dbobject.findVaccine(name);
	}
}
