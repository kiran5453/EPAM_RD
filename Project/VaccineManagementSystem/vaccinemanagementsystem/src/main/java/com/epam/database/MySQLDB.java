package com.epam.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

import com.epam.dataobjects.Address;
import com.epam.dataobjects.People;
import com.epam.dataobjects.Vaccine;

@Component
public class MySQLDB implements Database {

	private EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("my-local-mysql");
	private EntityManager eManager = eFactory.createEntityManager();

	public List<Vaccine> getVaccinedata() {
		return eManager.createQuery("select v from Vaccine v", Vaccine.class).getResultList();
	}

	public void addVaccineData(String name, Vaccine vaccine) {
		eManager.getTransaction().begin();
		eManager.persist(vaccine);
		eManager.getTransaction().commit();
	}

	public List<People> getPeopledata() {
		return eManager.createQuery("select p from People p", People.class).getResultList();
	}

	public void addPeopledata(String aadhar, People people) {
		eManager.getTransaction().begin();
		eManager.persist(people);
		eManager.getTransaction().commit();
	}

	public void increaseStock(Vaccine vaccine, long amount) {
		eManager.getTransaction().begin();
		vaccine.setInitialvaccinecount(vaccine.getInitialvaccinecount() + amount);
		vaccine.setBalancevaccinecount(vaccine.getBalancevaccinecount() + amount);
		eManager.getTransaction().commit();
	}

	@Override
	public Vaccine findVaccine(String name) {
		return eManager.find(Vaccine.class, name);
	}

	@Override
	public People findPeople(String aadhar) {
		return eManager.find(People.class, aadhar);
	}


	@Override
	public void decrementBalanceStock(Vaccine vaccine) {
		eManager.getTransaction().begin();
		vaccine.setBalancevaccinecount(vaccine.getBalancevaccinecount() - 1);
		eManager.getTransaction().commit();
	}
	
	public void addAddress(People people,List<Address> addresses) {
		eManager.getTransaction().begin();
		people.setAddress(addresses);
		eManager.getTransaction().commit();
	}

}
