//package com.epam.controller;
//
//import java.time.LocalDate;
//import java.util.Scanner;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.epam.dataobjects.Vaccine;
//import com.epam.service.VaccineService;
//
//@Component
//public class VaccineInput {
//
//	Scanner sc = new Scanner(System.in);
//	private static final Logger LOGGER = LogManager.getLogger(VaccineInput.class);
//	@Autowired
//	VaccineService vaccineService;
//
//	public void setDetails(String name) {
//		Vaccine vaccine = new Vaccine();
//		vaccine.setVaccineName(name);
//		do {
//			LOGGER.info("Enter date(yyyy-MM-dd) of vaccine arrival : ");
//			try {
//				vaccine.setDateOfVaccineArrival(LocalDate.parse(sc.next()));
//				break;
//			} catch (Exception e) {
//				LOGGER.error("Invalid Date Format");
//			}
//		} while (true);
//		do {
//			LOGGER.info("Enter initial count of vaccine : ");
//			long quantity = sc.nextLong();
//			if (quantity > 0) {
//				vaccine.setInitialVaccineCount(quantity);
//				vaccine.setBalanceVaccineCount(vaccine.getInitialVaccineCount());
//				break;
//			} else {
//				LOGGER.error("Quantity should not be neagtive\nEnter a value that is greater than 0");
//			}
//		} while (true);
//		vaccineService.insertVaccineDataToDatabase(vaccine.getVaccineName(), vaccine);
//		LOGGER.info("\n<---- Vaccine Details updated successfully ---->\n\n");
//	}
//
//	public void manageVaccine() {
//		LOGGER.info("\nWhat you would like to do :\n1. Add new vaccine\n2. Add stock\nSelect from above options : ");
//		String choice = sc.next();
//		switch (choice) {
//		case "1":
//			addNewVaccine();
//			break;
//		case "2":
//			receiveStock();
//			break;
//		default:
//			LOGGER.error("Invalid Selection...\n");
//		}
//	}
//
//	private void receiveStock() {
//		LOGGER.info("Enter name of the vaccine : ");
//		String name = sc.next();
//		name = name.toLowerCase();
//		Vaccine vaccine = vaccineService.findVaccineInDatabase(name);
//		if (vaccine == null) {
//			LOGGER.info("Vaccine doesn't exist...\nAdding new vaccine with name : " + name + "\n");
//			setDetails(name);
//		} else {
//			do {
//				LOGGER.info("Enter the quantity you would like to add : ");
//				long quantity = sc.nextLong();
//				if (quantity > 0) {
//					vaccineService.addStock(name, quantity);
//					break;
//				} else {
//					LOGGER.error("Quantity should not be neagtive\nEnter a value that is greater than 0");
//				}
//			} while (true);
//		}
//		LOGGER.info("<--- Vaccine stock updated successfully --->\n");
//
//	}
//
//	public void addNewVaccine() {
//		LOGGER.info("Enter name of the vaccine : ");
//		String name = sc.next();
//		name = name.toLowerCase();
//		if (vaccineService.findVaccineInDatabase(name) != null) {
//			LOGGER.info("Vaccine already exist...\nWould you like to add stock ? : ");
//			String stockchoice = sc.next();
//			if (stockchoice.equalsIgnoreCase("yes")) {
//				do {
//					LOGGER.info("Enter the quantity you would like to add : ");
//					long quantity = sc.nextLong();
//					if (quantity > 0) {
//						vaccineService.addStock(name, quantity);
//						break;
//					} else {
//						LOGGER.error("Quantity should nt be neagtive\nEnter a value that is greater than 0");
//					}
//				} while (true);
//			}
//			LOGGER.info("<--- Vaccine stock updated successfully --->\n");
//		} else {
//			setDetails(name);
//		}
//	}
//
//}
