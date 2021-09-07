package com.epam.dataobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private int id;
	@Getter @Setter private String type;
	@Getter @Setter private String street;
	@Getter @Setter private String city;
	@Getter @Setter private String state;
	@Getter @Setter private int pincode;

	@ManyToOne(targetEntity = People.class)
	@JoinColumn(name = "aadharnumber")
	@Setter private People people;

	@Override
	public String toString() {
		return "type=" + type + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode;
	}
}
