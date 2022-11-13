package com.shipwaylogistics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipwaylogistics.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);
}
