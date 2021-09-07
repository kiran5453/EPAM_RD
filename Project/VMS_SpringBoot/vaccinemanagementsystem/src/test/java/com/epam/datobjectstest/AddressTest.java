package com.epam.datobjectstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dataobjects.Address;

@ExtendWith(MockitoExtension.class)
class AddressTest {

	@InjectMocks
	Address address;
	String expectedString="type=" + "Home" + ", street=" + "gdpd" + ", city=" + "gnt" + ", state=" + "ap"
			+ ", pincode=" + "522403";
	
	@BeforeEach
	void setup() {
		address.setId(1);
		address.setType("Home");
		address.setStreet("gdpd");
		address.setCity("gnt");
		address.setPincode(522403);
		address.setState("ap");
	}

	@Test
	void getIdTest() {
		assertEquals(1,address.getId());
	}
	@Test
	void getAddressTypeTest() {
		assertEquals("Home",address.getType());
	}
	@Test
	void getStreetTest() {
		assertEquals("gdpd",address.getStreet());
	}
	@Test
	void getCityTest() {
		assertEquals("gnt",address.getCity());
	}
	@Test
	void getPincodeTest() {
		assertEquals(522403,address.getPincode());
	}
	@Test
	void getStateTest() {
		assertEquals("ap",address.getState());
	}
	@Test
	void Test_toString() {
		assertEquals(expectedString,address.toString());
	}


}
