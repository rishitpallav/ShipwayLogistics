package com.shipwaylogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipwaylogistics.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	Administrator findByEmail(String email);
}
