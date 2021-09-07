//package com.epam.database;
//package epam.database;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import epam.console.Address;
//import epam.console.People;
//import epam.console.Vaccine;
//
//public class CollectionDB implements Database {
//
//	private Map<String, People> peopledata = new HashMap<>();
//	private Map<String, Vaccine> vaccinedata = new HashMap<>();
//
//	public List<Vaccine> getVaccinedata() {
//		return new ArrayList<Vaccine>(vaccinedata.values());
//	}
//
//	public void addVaccineData(String name, Vaccine vaccine) {
//		vaccinedata.put(name, vaccine);
//	}
//
//	public List<People> getPeopledata() {
//		return new ArrayList<People>(peopledata.values());
//	}
//
//	public void addPeopledata(String aadhar, People people) {
//		peopledata.put(aadhar, people);
//	}
//
//	public void increaseStock(Vaccine vaccine, long amount) {
//		vaccine.setInitialvaccinecount(vaccine.getInitialvaccinecount() + amount);
//		vaccine.setBalancevaccinecount(vaccine.getBalancevaccinecount() + amount);
//	}
//
//	public Vaccine findVaccine(String name) {
//		Vaccine vaccine = null;
//		if (vaccinedata.containsKey(name))
//			vaccine = vaccinedata.get(name);
//		return vaccine;
//
//	}
//
//	@Override
//	public People findPeople(String aadhar) {
//		People people = null;
//		if (peopledata.containsKey(aadhar))
//			people = peopledata.get(aadhar);
//		return people;
//	}
//
//	@Override
//	public void decrementBalanceStock(Vaccine vaccine) {
//		vaccine.setBalancevaccinecount(vaccine.getBalancevaccinecount() - 1);
//	}
//
//	@Override
//	public void addAddress(People people, List<Address> addresses) {
//		
//		people.setAddress(addresses);
//	}
//
//}
