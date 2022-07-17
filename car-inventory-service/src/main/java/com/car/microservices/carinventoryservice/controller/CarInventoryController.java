package com.car.microservices.carinventoryservice.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.microservices.carinventoryservice.constants.Constants;
import com.car.microservices.carinventoryservice.dto.Car;
import com.car.microservices.carinventoryservice.dto.Response;
import com.car.microservices.carinventoryservice.repository.CarInventoryRepository;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping(path = "/car-inventory")
public class CarInventoryController {

	@Autowired
	private Environment environment; 
	
	@Autowired
	private CarInventoryRepository repository;
	
	@GetMapping("/getListOfCars")
	public Response getListOfCars(@RequestParam String status){
		
		String port = environment.getProperty("local.server.port");
		Response response = new Response();
		
		if(StringUtils.isEmpty(status)) {
			response.setErrorMsg("Status cannot be empty");
			response.setStatusCode(1);
		}
		else {
			if(Constants.STATUS_AVAILABLE.equalsIgnoreCase(status) || Constants.STATUS_SOLD.equalsIgnoreCase(status)) {
				response.setO(repository.findByStatusIgnoreCase(status));
				response.setErrorMsg(Constants.ERR_MSG_SUCCESS);
			}
			else if(Constants.STATUS_ALL.equalsIgnoreCase(status)){
				response.setO(repository.findAll());
				response.setErrorMsg(Constants.ERR_MSG_SUCCESS);
			}
			else {
				response.setErrorMsg("No Such status");
				response.setStatusCode(1);
			}			
		}

		response.setEnvironment(port);
		return response;
	}
	
	@PostMapping("/buyNewCar")
	public Response buyNewCar(@RequestParam Integer carId, @RequestParam BigDecimal offerAmt){
		
		String port = environment.getProperty("local.server.port");
		Response response = new Response();
		
		Optional<Car> optionalCar = repository.findById(carId);
		
		if(!optionalCar.isPresent()) {
			response.setErrorMsg("No such car in the listing");
			response.setStatusCode(1);
		}
		else {
			Car availableCar = optionalCar.get();
			if(!Constants.STATUS_AVAILABLE.equalsIgnoreCase(availableCar.getStatus())) {
				response.setErrorMsg("Car is sold and not available");
				response.setStatusCode(1);
			}
			else if(offerAmt.compareTo(availableCar.getPrice()) < 0) {
				response.setErrorMsg("The amount you offer is less than the car price. Please pay $"+availableCar.getPrice().subtract(offerAmt) + " more");
				response.setStatusCode(1);
			}
			else {
				availableCar.setStatus(Constants.STATUS_SOLD);
				repository.save(availableCar);
				
				response.setO(availableCar);
				if(offerAmt.compareTo(availableCar.getPrice()) == 0) {
					response.setErrorMsg("Thank you for buying. This is your car.");
				}
				else {
					response.setErrorMsg("Thank you for buying. This is your car and this is your change: $" + offerAmt.subtract(availableCar.getPrice()));
				}
			}
		}
		response.setEnvironment(port);
		return response;
	}
	
	@PostMapping("/addNewCar")
	public Response addNewCar(@RequestBody Car addCar){
		
		String port = environment.getProperty("local.server.port");
		Response response = new Response();
		
		if(ObjectUtils.isEmpty(addCar)) {
			response.setErrorMsg("Car cannot be empty");
			response.setStatusCode(1);
		}
		else {
			if(StringUtils.isEmpty(addCar.getBrand()) || StringUtils.isEmpty(addCar.getModel()) || ObjectUtils.isEmpty(addCar.getYearMake()) 
					|| ObjectUtils.isEmpty(addCar.getPrice())) {
				
				response.setErrorMsg("Brand, Model, Year Make or Price cannot be empty");
				response.setStatusCode(1);
			}
			else {
				addCar.setStatus(Constants.STATUS_AVAILABLE);
				Car newCar = repository.saveAndFlush(addCar);
				
				response.setO(repository.findAll());
				response.setErrorMsg("Car id " + newCar.getId() + " is added into the list");
			}
		}
		response.setEnvironment(port);
		return response;
	}
	
	@DeleteMapping("/removeCar")
	public Response removeCar(@RequestParam Integer carId){
		
		String port = environment.getProperty("local.server.port");
		Response response = new Response();
		
		if(ObjectUtils.isEmpty(carId)) {
			response.setErrorMsg("Car Id cannot be empty");
			response.setStatusCode(1);
		}
		else {
			
			Optional<Car> optionalCar = repository.findById(carId);
			
			if(!optionalCar.isPresent()) {
				response.setErrorMsg("No such car in the listing");
				response.setStatusCode(1);
			}
			else {
				repository.delete(optionalCar.get());
				response.setO(repository.findAll());
				response.setErrorMsg("Car id " + carId + " is remove from the list");
			}
			
		}
		response.setEnvironment(port);
		return response;
	}
}
