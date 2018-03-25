package com.assig.assig1.presenters;

import com.assig.assig1.userinterface.professor.IProfessorPresenter;

import java.util.List;

public interface IStudentInfoForProfessorView {
    void setPresenter(IProfessorPresenter professorPresenter);

    void setStudentName(String s);

    void setIdentificationNumber(String identificationNumber);

    void setGroup(String groupNumber);

    void display();

    void dontDisplay();

    void setJoinedCourses(List<String> courses);
}
