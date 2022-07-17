package com.car.microservices.carbuyerservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.microservices.carbuyerservice.dto.Response;
import com.car.microservices.carbuyerservice.proxy.CarInventoryProxy;

@RestController
@RequestMapping(path = "/car-buyer")
public class CarBuyerController {

	@Autowired
	private CarInventoryProxy proxy;
	
	@GetMapping("/getListOfCars")
	public Response getListOfCars(@RequestParam String status){
		return proxy.getListOfCars(status);
	}
	
	@PostMapping("/buyNewCar")
	public Response buyNewCar(@RequestParam Integer carId, @RequestParam BigDecimal offerAmt){
		return proxy.buyNewCar(carId, offerAmt);
	}
}
