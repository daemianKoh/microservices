package com.car.microservices.carsellerservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.microservices.carsellerservice.dto.Response;

@FeignClient(name="car-inventory")
public interface CarInventoryProxy {

	@GetMapping("/car-inventory/getListOfCars")
	public Response getListOfCars(@RequestParam String status);
}
