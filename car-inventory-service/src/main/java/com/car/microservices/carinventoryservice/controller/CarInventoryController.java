package com.car.microservices.carinventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
}
