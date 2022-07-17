package com.car.microservices.carbuyerservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.microservices.carbuyerservice.dto.Response;

@FeignClient(name="car-inventory")
public interface CarInventoryProxy {

	@GetMapping("/car-inventory/getListOfCars")
	public Response getListOfCars(@RequestParam String status);
}
