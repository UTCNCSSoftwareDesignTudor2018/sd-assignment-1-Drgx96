package com.assig.assig1.models;

public class User {
	private int id;
	private String username;
	private String lastname;
	private String firstname;
	private String phone;
	private String email;
	private Boolean admin;
	private String identitycardnumber;
	private int group_id;
	private String passwordMD5;
	
	public String getPasswordMD5() {
		return passwordMD5;
	}

	public void setPasswordMD5(String passwordMD5) {
		this.passwordMD5 = passwordMD5;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getIdentitycardnumber() {
		return identitycardnumber;
	}

	public void setIdentitycardnumber(String identitycardnumber) {
		this.identitycardnumber = identitycardnumber;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public User(int id, String username, String lastname, String firstname, String phone, String email, Boolean admin,
			String identitycardnumber, int group_id, String passwordMD5) {
		super();
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.firstname = firstname;
		this.phone = phone;
		this.email = email;
		this.admin = admin;
		this.identitycardnumber = identitycardnumber;
		this.group_id = group_id;
		this.passwordMD5 = passwordMD5;
	}

	public String getFirstName() {
		return firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public String getICN() {
		return identitycardnumber;
	}

	public String getAddress() {
		return null;
	}

	public String getUsername() {
		return username;
	}

	public int getId()
	{
		return id;
	}
}
