package com.epam.service;

import org.springframework.stereotype.Component;

import com.epam.database.Database;
import com.epam.database.MySQLDB;

@Component
public class MainService {

	private static Database db = new MySQLDB();
	
	public static Database getDBObject() {
		return db;
	}
}
