package com.shipwaylogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipwaylogistics.model.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {

	DeliveryPartner findByEmail(String email);
}
