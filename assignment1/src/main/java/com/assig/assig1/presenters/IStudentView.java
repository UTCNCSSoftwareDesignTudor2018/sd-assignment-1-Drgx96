package com.assig.assig1.presenters;

import com.assig.assig1.userinterface.student.IStudentPresenter;

public interface IStudentView {

    void display();

    void dontDisplay();

    void setPresenter(IStudentPresenter studentPresenter);
}
