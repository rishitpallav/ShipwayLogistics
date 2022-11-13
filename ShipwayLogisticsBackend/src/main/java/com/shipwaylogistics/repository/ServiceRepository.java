package com.shipwaylogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shipwaylogistics.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

	@Query(value = "SELECT partner_service_fk FROM service WHERE id = ?1", nativeQuery = true)
	Integer getDeliveryPartnerId(int id);
}
