package com.epam.dataobjects;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.*;

@Entity
@Table(name = "vaccine")
public class Vaccine {
	@Id
	private String vaccineName;
	private LocalDate dateOfVaccineArrival;
	private long initialVaccineCount;
	private long balanceVaccineCount;

	public String getVaccinename() {
		return vaccineName;
	}

	public void setVaccinename(String vaccinename) {
		this.vaccineName = vaccinename;
	}

	public LocalDate getDateofvaccinearrival() {
		return dateOfVaccineArrival;
	}

	public void setDateofvaccinearrival(LocalDate dateofvaccinearrival) {
		this.dateOfVaccineArrival = dateofvaccinearrival;
	}

	public long getInitialvaccinecount() {
		return initialVaccineCount;
	}

	public void setInitialvaccinecount(long initialvaccinecount) {
		this.initialVaccineCount = initialvaccinecount;
	}

	public long getBalancevaccinecount() {
		return balanceVaccineCount;
	}

	public void setBalancevaccinecount(long balancevaccinecount) {
		this.balanceVaccineCount = balancevaccinecount;
	}
}
