package com.epam.dto;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


public class PeopleDTO {

	@Getter @Setter private String name;
	@Min(value = 15, message = "age should be greater than 15")
	@Getter @Setter private int age;
	@Id
    @NotNull(message = "aadhar number should not be null")
	@Size(min = 12, message = "aadhar shoud be 12 digits")
	@Getter @Setter String aadharNumber;
	@Getter @Setter private String firstDoseDate = "";
	@Getter @Setter private String secondDoseDate = "";
	@Getter @Setter private String vaccineType = "";
}
