package com.assig.assig1.models;

public class Address {
    private long id;
    private String country;
    private String city;
    private String street;
    private String number;
    private String buildingnumber;
    private String floor;
    private String doornumber;
    
	public Address(long id, String country, String city, String street, String number, String buildingnumber,
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

    public Address(String country, String city, String street, String number, String buildingnumber,
                   String floor, String doornumber) {
        super();
        this.id = java.util.UUID.randomUUID().timestamp();
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.buildingnumber = buildingnumber;
        this.floor = floor;
        this.doornumber = doornumber;
    }

    public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBuildingnumber() {
		return buildingnumber;
	}

	public void setBuildingnumber(String buildingnumber) {
		this.buildingnumber = buildingnumber;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDoornumber() {
		return doornumber;
	}

	public void setDoornumber(String doornumber) {
		this.doornumber = doornumber;
	}

	public long getId() {
		return id;
	}

}
