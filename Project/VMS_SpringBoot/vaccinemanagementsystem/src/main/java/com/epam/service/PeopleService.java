package com.epam.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dataobjects.People;
import com.epam.repositories.PeopleRepository;

@Service
public class PeopleService {

	@Autowired
	VaccineService vaccineservice;
	@Autowired
	PeopleRepository peopleRepo;

	public boolean validateAadhar(String aadhar) {
		String regex = "[2-9]{1}[0-9]{11}";
		boolean isvalid = false;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(aadhar);
		if (m.matches())
			isvalid = true;
		return isvalid;
	}

	public String getVaccinated(People person, String name) {
		String output = "";
		if (person.getFirstDoseDate() != null && person.getSecondDoseDate() != null) {
			output = "\nPerson had already completed 2 doses of vaccine...\nPerson can only take 2 doses of vaccine\nStay Safe\n";
		} else if (person.getFirstDoseDate() == null) {
			output = issueFirstDose(person, name);
		} else {
			output = issueSecondDose(person);
		}
		return output;
	}

	public String issueFirstDose(People person, String name) {
		int gapbetweendoses = 21;
		String result;
		if (!vaccineservice.vaccineStockAvailable(name)) {
			result = "<-- Vaccine out of Stock -->";
		} else {
			person.setFirstDoseDate(LocalDate.now());
			person.setVaccineType(name);
			peopleRepo.save(person);
			vaccineservice.decrementVaccineCount(name);
			result = "\nReceived first dose of vaccine...\nPlease visit after " + gapbetweendoses
					+ " days for the second dose\n\n";
		}
		return result;
	}

	public String issueSecondDose(People person) {
		int gapbetweendoses = 21;
		String name;
		String result;
		long duration = findDuration(person.getFirstDoseDate(), LocalDate.now());
		if (duration > gapbetweendoses) {
			if (vaccineservice.vaccineStockAvailable(person.getVaccineType())) {
				person.setSecondDoseDate(LocalDate.now());
				peopleRepo.save(person);
				name = person.getVaccineType();
				result = "\nSuccessfully completed 2 doses of vaccine...\nStay Safe\n";
				vaccineservice.decrementVaccineCount(name);
			} else {
				result = "<-- Vaccine out of Stock -->\n";
			}
		} else {
			result = "You should have a gap of " + gapbetweendoses + " days between two doses\nPlease visit after "
					+ (gapbetweendoses - duration) + " days\n";
		}
		return result;
	}

	public long findDuration(LocalDate first, LocalDate second) {
		return ChronoUnit.DAYS.between(first, second);
	}

	public void insertPeopleDataToDatabase(String aadhar,People people) {
		peopleRepo.save(people);
	}
	
	public List<People> getPeopleDataFromDatabase() {
		return (List<People>)peopleRepo.findAll();
	}
	
	public People findPeopleInDatabase(String aadhar) {
		return peopleRepo.findByAadharNumber(aadhar);
	}

	public void updatePeopleAddress(People people) {
		peopleRepo.save(people);
	}
}
