package com.shipwaylogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipwaylogistics.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
