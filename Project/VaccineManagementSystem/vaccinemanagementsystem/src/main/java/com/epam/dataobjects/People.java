package com.epam.dataobjects;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.*;

@Entity
@Table(name = "People")
public class People {
	private String name;
	private int age;
	@Id
	private String aadharNumber;
	private LocalDate firstDoseDate = null;
	private LocalDate secondDoseDate = null;
	private String vaccineType = "null";

	@OneToMany(mappedBy = "people", cascade = CascadeType.PERSIST)
	private List<Address> addresses = new ArrayList<>();

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddress(List<Address> addresses) {
		addresses.forEach(addr -> addr.setPeople(this));
		this.addresses.addAll(addresses);
	}

	static Scanner sc = new Scanner(System.in);

	public String getAadharnumber() {
		return aadharNumber;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public LocalDate getFirstdosedate() {
		return firstDoseDate;
	}

	public LocalDate getSeconddosedate() {
		return secondDoseDate;
	}

	public void setFirstdosedate(LocalDate firstdosedate) {
		this.firstDoseDate = firstdosedate;
	}

	public void setVaccinetype(String vaccinetype) {
		this.vaccineType = vaccinetype;
	}

	public void setSeconddosedate(LocalDate seconddosedate) {
		this.secondDoseDate = seconddosedate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAadharnumber(String aadhar) {
		this.aadharNumber = aadhar;
	}

	public String getVaccinetype() {
		return vaccineType;
	}

}