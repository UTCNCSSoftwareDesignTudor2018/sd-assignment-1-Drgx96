package com.assig.assig1.models;

import java.util.List;

public class StudentInformation {
	String studentID;
	String groupNumber;
	List<Class> classes;
	
	public StudentInformation(String studentID, String groupNumber, List<Class> classes) {
		super();
		this.studentID = studentID;
		this.groupNumber = groupNumber;
		this.classes = classes;
	}

	public String getIdentificationNumber() {
		return studentID;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public List<Class> getClasses() {
		return classes;
	}
}
