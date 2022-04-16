package com.ankit.demo;

import org.springframework.stereotype.Component;

@Component
public class Address {
	private int addressId;
	private String street;
	private String city;
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void printAddress() {
		System.out.println("Inside print method of Address");
	}

}
