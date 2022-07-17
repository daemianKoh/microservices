package com.car.microservices.carsellerservice.dto;

import java.math.BigDecimal;

public class Car {
	
	private int id;
	private String brand;
	private String model;
	private Integer yearMake;
	private BigDecimal price;
	private String status;
	
	public Car() {
		
	}

	public Car(int id, String brand, String model, int yearMake, BigDecimal price, String status) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.yearMake = yearMake;
		this.price = price;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYearMake() {
		return yearMake;
	}

	public void setYearMake(int yearMake) {
		this.yearMake = yearMake;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
