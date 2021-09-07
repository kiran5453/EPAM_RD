package com.epam.dataobjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "People")
public class People {
	@Getter @Setter private String name;
	@Min(value = 15, message = "age should be greater than 15")
	@Getter @Setter private int age;
	@Id
	@NotNull(message = "aadhar number should not be null")
	@Size(min = 12, message = "aadhar shoud be 12 digits")
	@Getter @Setter private String aadharNumber;
	@Getter @Setter private LocalDate firstDoseDate = null;
	@Getter @Setter private LocalDate secondDoseDate = null;
	@Getter @Setter private String vaccineType = "null";

	@OneToMany(mappedBy = "people", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@Getter @Setter private List<Address> addresses = new ArrayList<>();
}