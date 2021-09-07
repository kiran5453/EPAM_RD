package com.epam;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
public class VaccinemanagementsystemApplication {

	
	static Scanner sc = new Scanner(System.in);
	//private static final Logger LOGGER = LogManager.getLogger(VaccinemanagementsystemApplication.class);

	//@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(VaccinemanagementsystemApplication.class, args);
		/*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(VaccinemanagementsystemApplication.class);
		String aadhar;
		String name;
		PeopleInput peopleInput = applicationContext.getBean(PeopleInput.class);
		VaccineInput vaccineInput = applicationContext.getBean(VaccineInput.class);
		PeopleDisplay peopledisplay = applicationContext.getBean(PeopleDisplay.class);
		VaccineDisplay vaccinedisplay = applicationContext.getBean(VaccineDisplay.class);
		while (true) {
			LOGGER.info("-----Vaccine Management System-----"
					+"\n1. Manage Vaccine"
					+"\n2. Manage People"
					+"\n3. Get Vaccine Details in Database"
					+"\n4. Get People Details in Database"
					+"\n5. Get Vaccine Details based on Name"
					+"\n6. Get Person Details based on Aadhar Number"
					+"\n7. Exit");
			LOGGER.info("\nSelect What you would like to do : ");
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
		}*/
	}
}
