package com.car.microservices.carinventoryservice.dto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="car_id_seq", initialValue=5)
public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="car_id_seq")
	private Integer id;
	private String brand;
	private String model;
	private Integer yearMake;
	private BigDecimal price;
	private String status;
	
	public Car() {
		
	}

	public Car(Integer id, String brand, String model, Integer yearMake, BigDecimal price, String status) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.yearMake = yearMake;
		this.price = price;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getYearMake() {
		return yearMake;
	}

	public void setYearMake(Integer yearMake) {
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
