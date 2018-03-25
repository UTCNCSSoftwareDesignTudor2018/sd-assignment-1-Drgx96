package com.assig.assig1;

import com.assig.assig1.userinterface.professor.IProfessorPresenter;

public interface IProfessorView {

	void showStudents();

	void dontDisplay();

	void setPresenter(IProfessorPresenter professorPresenter);

	void display();
}
