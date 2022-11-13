package com.shipwaylogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipwaylogistics.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

	
}
