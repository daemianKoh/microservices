package com.car.microservices.carsellerservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.microservices.carsellerservice.dto.Response;

@FeignClient(name="car-inventory", url="localhost:8080/car-inventory")
public interface CarInventoryProxy {

	@GetMapping("/getListOfCars")
	public Response getListOfCars(@RequestParam String status);
}
