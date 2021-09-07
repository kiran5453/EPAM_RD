package com.epam.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dataobjects.Vaccine;
import com.epam.service.VaccineService;

@Component
public class VaccineDisplay {
	private static final Logger LOGGER = LogManager.getLogger(VaccineDisplay.class);
	@Autowired
	VaccineService vaccineService;
	public void getVaccineDetails() {
		List<Vaccine> vaccines = vaccineService.getVaccineDataFromDatabase();
		if (!vaccines.isEmpty()) {
			LOGGER.info("\n*******************************Vaccine Details********************************\n");
			vaccines.forEach(vaccine -> LOGGER.info("Name  : " + vaccine.getVaccinename() + "\nDate of arrival : "
					+ vaccine.getDateofvaccinearrival() + "\nInitial amount of vaccine : " + vaccine.getInitialvaccinecount()
					+ "\nBalance amount of vaccine : " + vaccine.getBalancevaccinecount()
					+ "\n-----------------------------------------------------"));
			LOGGER.info("*******************************************************************************");
		} else {
			LOGGER.error("<-- No vaccines available -->");
		}
	}

	public void getVaccineDetailsBasedOnName(String name) {
		Vaccine vaccine = vaccineService.findVaccineInDatabase(name);
		if (vaccine != null) {
			LOGGER.info("\nVaccine Details with Name " + name + ":\n");
			LOGGER.info("Name  : " + vaccine.getVaccinename() + "\nDate of arrival : " + vaccine.getDateofvaccinearrival()
					+ "\nInitial amount of vaccine : " + vaccine.getInitialvaccinecount()
					+ "\nBalance amount of vaccine : " + vaccine.getBalancevaccinecount() + "\n\n");
		} else {
			LOGGER.error("\nNo Vaccine available with given Name\n\n");
		}
	}
}
