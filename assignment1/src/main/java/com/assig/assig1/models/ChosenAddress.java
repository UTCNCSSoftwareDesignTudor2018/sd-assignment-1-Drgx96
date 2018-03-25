package com.assig.assig1.models;

public class ChosenAddress {
    private int users_id;
    private int address_id;

    public ChosenAddress(int users_id, int address_id) {
        super();
        this.users_id = users_id;
        this.address_id = address_id;
    }

	public int getUsers_id() {
		return users_id;
	}

	public int getAddress_id() {
		return address_id;
	}
}
