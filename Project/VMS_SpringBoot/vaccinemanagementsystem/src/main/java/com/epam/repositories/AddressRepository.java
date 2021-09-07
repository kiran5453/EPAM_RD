package com.epam.repositories;

import org.springframework.data.repository.CrudRepository;

import com.epam.dataobjects.Address;

public interface AddressRepository extends CrudRepository<Address,Integer> {

}
