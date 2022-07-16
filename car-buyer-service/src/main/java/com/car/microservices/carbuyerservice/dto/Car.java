package com.car.microservices.carbuyerservice.dto;

import java.math.BigDecimal;

public class Car {
	
	private Long id;
	private String brand;
	private String modal;
	private int yearMake;
	private BigDecimal price;
	private String status;
	
	public Car() {
		
	}

	public Car(Long id, String brand, String modal, int yearMake, BigDecimal price, String status) {
		super();
		this.id = id;
		this.brand = brand;
		this.modal = modal;
		this.yearMake = yearMake;
		this.price = price;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
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
