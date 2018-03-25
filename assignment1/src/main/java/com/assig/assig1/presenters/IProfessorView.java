package com.assig.assig1.presenters;

import com.assig.assig1.userinterface.professor.IProfessorPresenter;

public interface IProfessorView {

    void dontDisplay();

    void setPresenter(IProfessorPresenter professorPresenter);

    void display();
}
