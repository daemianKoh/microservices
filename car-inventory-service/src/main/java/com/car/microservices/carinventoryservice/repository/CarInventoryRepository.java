package com.car.microservices.carinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.microservices.carinventoryservice.dto.Car;

public interface CarInventoryRepository extends JpaRepository<Car, Long>{

	List<Car> findByStatusIgnoreCase(String status);
}
