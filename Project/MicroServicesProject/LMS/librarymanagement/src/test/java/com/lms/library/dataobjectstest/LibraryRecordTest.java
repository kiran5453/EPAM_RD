package com.lms.library.dataobjectstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.library.datobjects.LibraryRecord;

@SpringBootTest
class LibraryRecordTest {

	LibraryRecord libraryRecord;
	
	@BeforeEach
	void setup() {
		libraryRecord = new LibraryRecord();
		libraryRecord.setId(1);
		libraryRecord.setBookId(101);
		libraryRecord.setUserName("kiran5453");
	}
	
	@Test
	void getIdTest() {
		assertEquals(1,libraryRecord.getId());
	}
	
	@Test
	void getBookIdTest() {
		assertEquals(101,libraryRecord.getBookId());
	}
	
	@Test
	void getUserNameTest() {
		assertEquals("kiran5453",libraryRecord.getUserName());
	}
}
