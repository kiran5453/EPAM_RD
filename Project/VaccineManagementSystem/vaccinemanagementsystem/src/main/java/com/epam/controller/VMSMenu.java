package com.epam.controller;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.epam.controller","com.epam.database","com.epam.service","com.epam.datobjects"})
public class VMSMenu {
	static Scanner sc = new Scanner(System.in);
	private static final Logger LOGGER = LogManager.getLogger(VMSMenu.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String aadhar;
		String name;
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(VMSMenu.class);
		PeopleInput peopleInput = applicationContext.getBean(PeopleInput.class);
		VaccineInput vaccineInput = applicationContext.getBean(VaccineInput.class);
		PeopleDisplay peopledisplay = applicationContext.getBean(PeopleDisplay.class);
		VaccineDisplay vaccinedisplay = applicationContext.getBean(VaccineDisplay.class);
		while (true) {
			LOGGER.info("-----Vaccine Management System-----\n1. Manage Vaccine\n2. Manage People"
					+ "\n3. Get Vaccine Details in Database"
					+ "\n4. Get People Details in Database\n5. Get Vaccine Details based on Name"
					+ "\n6. Get Person Details based on Aadhar Number\n7. Exit\n");
			LOGGER.info("Select What you would like to do : ");
			String choice = sc.next();
			switch (choice) {
			case "1":
				vaccineInput.manageVaccine();
				break;
			case "2":
				peopleInput.managePeople();
				break;
			case "3":
				vaccinedisplay.getVaccineDetails();
				break;
			case "4":
				peopledisplay.getPeopleDetails();
				break;
			case "5":
				LOGGER.info("Enter name of the vaccine : ");
				name = sc.next();
				vaccinedisplay.getVaccineDetailsBasedOnName(name);
				break;
			case "6":
				LOGGER.info("Enter aadhar number of the person : ");
				aadhar = sc.next();
				peopledisplay.getPersonDetailsBasedOnAadhar(aadhar);
				break;
			case "7":
				LOGGER.info("\nSuccessfully Logged out from VMS Portal");
				break;
			default:
				LOGGER.error("\nInvalid selection");
			}
			if (choice.equals("7"))
				break;
		}
	}
}
