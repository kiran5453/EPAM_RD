package com.epam.dataobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private String street;
	private String city;
	private String state;
	private int pincode;

	@ManyToOne(targetEntity = People.class)
	@JoinColumn(name = "aadharnumber")
	private People people;

	public void setPeople(People people) {
		this.people = people;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "type=" + type + ", street=" + street + ", city=" + city + ", state=" + state + ", pincode=" + pincode;
	}
}
