package com.epam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dataobjects.Address;
import com.epam.dataobjects.People;
import com.epam.service.PeopleService;
import com.epam.service.VaccineService;

@Component
public class PeopleInput {

	private static final Logger LOGGER = LogManager.getLogger(PeopleInput.class);
	Scanner sc = new Scanner(System.in);
	@Autowired
	PeopleService peopleService;
	@Autowired
	VaccineService vaccineService;

	public void setDetails(String aadhar) {
		People newPerson = new People();
		LOGGER.info("**Enter person details**\n");
		LOGGER.info("Enter name of the person : ");
		newPerson.setName(sc.next());
		giveAge(newPerson);
		newPerson.setAadharnumber(aadhar);
		giveFirstdosedate(newPerson);
		if (newPerson.getFirstdosedate() != null) {
			LOGGER.info("Enter vaccine type taken for first dose : ");
			newPerson.setVaccinetype(sc.next());
		}
		addAddresses(newPerson);
		peopleService.insertPeopleDataToDatabase(newPerson.getAadharnumber(), newPerson);
		LOGGER.info("-------------------- Person added succesfully --------------------");
	}

	public void giveAge(People newPerson) {
		do {
			LOGGER.info("Enter age of the person : ");
			try {
				String ageAsString = sc.next();
				int age = Integer.parseInt(ageAsString);
				if (age > 0) {
					newPerson.setAge(age);
					break;
				} else
					LOGGER.error("Age should be greater than 0");
			} catch (NumberFormatException e) {
				LOGGER.info("Enter valid age");
			}
		} while (true);
	}

	public void giveFirstdosedate(People newPerson) {
		boolean in = true;
		do {
			LOGGER.info("Enter date of the first dose(yyyy-MM-dd) : ");
			String firstdose = sc.next();
			if (firstdose.equalsIgnoreCase("null"))
				in = false;
			else {
				try {
					newPerson.setFirstdosedate(LocalDate.parse(firstdose));
					in = false;
				} catch (Exception e) {
					LOGGER.error("Invalid Date Format...");
				}
				if (!in && peopleService.findDuration(newPerson.getFirstdosedate(), LocalDate.now()) <= 0) {
					in = true;
					LOGGER.error("Please enter a date that is earlier than today's date");
				}
			}
		} while (in);
	}

	public String readVaccineType() {
		String type;
		while (true) {
			LOGGER.info("Please select any of the below vaccines for getting vaccinated\nAvailable vaccines :");
			vaccineService.getVaccineDataFromDatabase().forEach(v -> LOGGER.info(v.getVaccinename()));
			LOGGER.info("Select the vaccine from above list : ");
			type = sc.next();
			if (vaccineService.findVaccineInDatabase(type) != null)
				break;
			else
				LOGGER.info("Invalid selection...");
		}
		return type;
	}

	public void managePeople() {
		LOGGER.info(
				"What you would like to do :\n1. Add new person\n2. Add Address for existing person\n3. Get vaccinated\nSelect : ");
		String choice = sc.next();
		String aadhar;
		switch (choice) {
		case "1":
			aadhar = readAadhar();
			addNewPerson(aadhar);
			break;
		case "3":
			aadhar = readAadhar();
			giveVaccine(aadhar);
			break;
		case "2":
			aadhar = readAadhar();
			People people = peopleService.findPeopleInDatabase(aadhar);
			if (people != null)
				addAddresses(people);
			else
				LOGGER.error("Person doesn't exist");
			break;
		default:
			LOGGER.error("Invalid Selection...");
		}
	}

	public String readAadhar() {
		String aadhar;
		LOGGER.info("Enter Aadhar number without any spaces : ");
		while (true) {
			aadhar = sc.next();
			if (peopleService.validateAadhar(aadhar)) {
				break;
			} else {
				LOGGER.info("Invalid Aadhar number...\nPlease enter a valid aadhar number without any spaces :");
			}
		}
		return aadhar;
	}

	private void giveVaccine(String aadhar) {
		People people = peopleService.findPeopleInDatabase(aadhar);
		if (people == null) {
			LOGGER.info("User doesn't exist....\nCreating a new User\n");
			setDetails(aadhar);
			people = peopleService.findPeopleInDatabase(aadhar);
		}
		String type = "null";
		if (people.getFirstdosedate() == null)
			type = readVaccineType();
		LOGGER.info(peopleService.getVaccinated(people, type));

	}

	private void addNewPerson(String aadhar) {
		if (peopleService.findPeopleInDatabase(aadhar) != null) {
			LOGGER.info("\nUser already exists...\n");
		} else {
			setDetails(aadhar);
		}
	}

	public void addAddresses(People peopleobj) {
		List<Address> addr = new ArrayList<>();
		LOGGER.info("Enter the address :");
		Address address = new Address();
		LOGGER.info("Enter the street : ");
		address.setStreet(sc.next());
		LOGGER.info("Enter the city : ");
		address.setCity(sc.next());
		LOGGER.info("Enter the state : ");
		address.setState(sc.next());
		LOGGER.info("Enter the pin code : ");
		address.setPincode(sc.nextInt());
		LOGGER.info("Enter the type of address you have entered (Ex : Home , Office etc....) :");
		String type = sc.next();
		address.setType(type);
		address.setPeople(peopleobj);
		addr.add(address);
		peopleService.insertAddressToDatabase(peopleobj, addr);
		LOGGER.info("\n<---- Address added successfully ---->\n\n");
	}

}
