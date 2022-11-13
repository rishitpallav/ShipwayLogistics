package com.shipwaylogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipwaylogistics.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
