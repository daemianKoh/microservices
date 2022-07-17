package com.car.microservices.carsellerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.microservices.carsellerservice.dto.Car;
import com.car.microservices.carsellerservice.dto.Response;
import com.car.microservices.carsellerservice.proxy.CarInventoryProxy;

@RestController
@RequestMapping(path = "/car-seller")
public class CarSellerController {

	@Autowired
	private CarInventoryProxy proxy;
	
	@GetMapping("/getListOfCars")
	public Response getListOfCars(@RequestParam String status){
		
		return proxy.getListOfCars(status);
	}
	
	@PostMapping("/addNewCar")
	public Response addNewCar(@RequestBody Car addCar){
		return proxy.addNewCar(addCar);
	}
	
	@DeleteMapping("/removeCar")
	public Response removeCar(@RequestParam Integer carId){
		return proxy.removeCar(carId);
	}
}
