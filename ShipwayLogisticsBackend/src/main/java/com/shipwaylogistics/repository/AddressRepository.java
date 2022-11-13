package com.shipwaylogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipwaylogistics.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
