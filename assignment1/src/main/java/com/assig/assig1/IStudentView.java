package com.assig.assig1;

import com.assig.assig1.presenters.StudentPresenter;
import com.assig.assig1.userinterface.student.IStudentPresenter;

public interface IStudentView {

	void display();

	void dontDisplay();

	void setPresenter(IStudentPresenter studentPresenter);
}
