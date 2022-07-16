package com.car.microservices.carsellerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.car.microservices.carsellerservice.Constants.Constants;
import com.car.microservices.carsellerservice.dto.Response;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping(path = "/car-seller")
public class CarSellerController {

	@Autowired
	private Environment environment; 
	
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
				HttpHeaders headers = new HttpHeaders();
				headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
				
				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/car-inventory/getListOfCars")
						.queryParam("status", status);
				
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<Response> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), Response.class);
				
				return responseEntity.getBody();
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
