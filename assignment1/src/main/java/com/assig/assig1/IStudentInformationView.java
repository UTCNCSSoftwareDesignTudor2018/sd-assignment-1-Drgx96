package com.assig.assig1;

import java.util.List;

import com.assig.assig1.presenters.StudentPresenter;

public interface IStudentInformationView {

	void setIdentificationNumber(String identificationNumber);

	void setGroup(String groupNumber);

	void setEnrollments(List<String> enrollments);

	void setPresenter(StudentPresenter studentPresenter);

	void display();
}
