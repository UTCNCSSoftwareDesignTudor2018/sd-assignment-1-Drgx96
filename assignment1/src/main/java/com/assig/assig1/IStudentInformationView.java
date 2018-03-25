package com.assig.assig1;

import java.util.List;

import com.assig.assig1.presenters.StudentPresenter;

public interface IStudentInformationView {

	void setIdentificationNumber(String identificationNumber);

	void setGroup(String groupNumber);

	void showEnrollments(List<String> subjects);

	void setPresenter(StudentPresenter studentPresenter);

	void display();
}
