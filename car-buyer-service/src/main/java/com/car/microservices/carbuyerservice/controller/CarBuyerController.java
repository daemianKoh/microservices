package com.car.microservices.carbuyerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.microservices.carbuyerservice.Constants.Constants;
import com.car.microservices.carbuyerservice.dto.Response;
import com.car.microservices.carbuyerservice.proxy.CarInventoryProxy;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping(path = "/car-buyer")
public class CarBuyerController {

	@Autowired
	private Environment environment; 
	
	@Autowired
	private CarInventoryProxy proxy;
	
	@GetMapping("/getListOfCars")
	public Response getListOfCars(@RequestParam String status){
		
		String port = environment.getProperty("local.server.port");
		Response response = new Response();
		
		if(StringUtils.isEmpty(status)) {
			response.setErrorMsg("Status cannot be empty");
			response.setStatusCode(1);
			response.setEnvironment(port);
		}
		else {
			if(Constants.STATUS_ALL.equalsIgnoreCase(status)|| Constants.STATUS_AVAILABLE.equalsIgnoreCase(status) || Constants.STATUS_SOLD.equalsIgnoreCase(status)) {
				
				return proxy.getListOfCars(status);
			}
			else {
				response.setErrorMsg("No Such status");
				response.setStatusCode(1);
				response.setEnvironment(port);
			}			
		}
		return response;
	}
}
