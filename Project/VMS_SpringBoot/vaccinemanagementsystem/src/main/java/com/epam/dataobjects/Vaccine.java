package com.epam.dataobjects;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vaccine")
public class Vaccine {
	@Id
	@Getter @Setter private String vaccineName;
	@Getter @Setter private LocalDate dateOfVaccineArrival;
	@Getter @Setter private long initialVaccineCount;
	@Getter @Setter private long balanceVaccineCount;
}
