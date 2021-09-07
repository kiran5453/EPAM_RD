package com.epam.database;

import java.util.List;

import com.epam.dataobjects.Address;
import com.epam.dataobjects.People;
import com.epam.dataobjects.Vaccine;

public interface Database {

	public List<Vaccine> getVaccinedata();

	public void addVaccineData(String name, Vaccine vaccine);

	public List<People> getPeopledata();

	public void addPeopledata(String aadhar, People people);

	public void increaseStock(Vaccine vaccine, long amount);
	
	public void decrementBalanceStock(Vaccine vaccine);
	
	public void addAddress(People people,List<Address> addresses);
	
	public Vaccine findVaccine(String name);
	
	public People findPeople(String aadhar);
}