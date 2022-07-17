package com.car.microservices.carsellerservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.microservices.carsellerservice.dto.Car;
import com.car.microservices.carsellerservice.dto.Response;

@FeignClient(name="car-inventory")
public interface CarInventoryProxy {

	@GetMapping("/car-inventory/getListOfCars")
	public Response getListOfCars(@RequestParam String status);
	
	@PostMapping("/car-inventory/addNewCar")
	public Response addNewCar(@RequestBody Car addCar);
	
	@DeleteMapping("/car-inventory/removeCar")
	public Response removeCar(@RequestParam Integer carId);
}
