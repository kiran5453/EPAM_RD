//package com.epam.controller;
//
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.epam.dataobjects.People;
//import com.epam.service.PeopleService;
//
//@Component
//public class PeopleDisplay {
//	private static final Logger LOGGER = LogManager.getLogger(PeopleDisplay.class);
//
//	@Autowired
//	PeopleService peopleService;
//	public void getPeopleDetails() {
//		List<People> peoples = peopleService.getPeopleDataFromDatabase();
//		if (!peoples.isEmpty()) {
//			LOGGER.info("\n*******************************People Details********************************\n");
//			peoples.forEach(
//					person -> LOGGER.info("Name  : " + person.getName() + "\nAge : " + person.getAge() + "\nAddress : " + person.getAddresses()
//							+ "\nAadhar Number : " + person.getAadharNumber() + "\nType of vaccine taken : " + person.getVaccineType()
//							+ "\nFirst dose of vaccine : " + person.getFirstDoseDate() + "\nSecond dose of vaccine : "
//							+ person.getSecondDoseDate() + "\n------------------------------------------"));
//			LOGGER.info("*******************************************************************************");
//		} else {
//			LOGGER.error("People not available...\n");
//		}
//	}
//
//	public void getPersonDetailsBasedOnAadhar(String aadhar) {
//		People person = peopleService.findPeopleInDatabase(aadhar);
//		if (person != null) {
//			LOGGER.info("\nPerson Details with Aadhar Number " + aadhar + ":\n");
//			LOGGER.info("Name : " + person.getName());
//			LOGGER.info("Age : " + person.getAge());
//			LOGGER.info("Address : " + person.getAddresses());
//			LOGGER.info("Aadhar Number : " + person.getAadharNumber());
//			LOGGER.info("Type of vaccine taken : " + person.getVaccineType());
//			LOGGER.info("First dose of vaccine : " + person.getFirstDoseDate());
//			LOGGER.info("Second dose of vaccine : " + person.getSecondDoseDate() + "\n");
//		} else {
//			LOGGER.info("\nNo Person available with given Aadhar Number\n");
//		}
//	}
//}
