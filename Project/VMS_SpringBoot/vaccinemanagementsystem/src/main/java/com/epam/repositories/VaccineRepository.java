package com.epam.repositories;

import org.springframework.data.repository.CrudRepository;

import com.epam.dataobjects.Vaccine;

public interface VaccineRepository extends CrudRepository<Vaccine,String>{

	Vaccine findByVaccineName(String name);
}
