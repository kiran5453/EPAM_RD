package com.epam.dto;

import lombok.Getter;
import lombok.Setter;

public class VaccineDTO {

	@Getter @Setter private String vaccineName;
	@Getter @Setter private String dateOfVaccineArrival;
	@Getter @Setter private long initialVaccineCount;
	@Getter @Setter private long balanceVaccineCount;
	
}
