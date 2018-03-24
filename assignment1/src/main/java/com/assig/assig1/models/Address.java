package com.assig.assig1.models;

public class Address {
	private int id;
	private String country;
	private String city;
	private String street;
	private String number;
	private String buildingnumber;
	private String floor;
	private String doornumber;
	public Address(int id, String country, String city, String street, String number, String buildingnumber,
			String floor, String doornumber) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.buildingnumber = buildingnumber;
		this.floor = floor;
		this.doornumber = doornumber;
	}
	
}
