package com.assig.assig1.presenters;

import java.util.List;

public interface IStudentInformationView {

    void setIdentificationNumber(String identificationNumber);

    void setGroup(String groupNumber);

    void showEnrollments(List<String> subjects);

    void setPresenter(StudentPresenter studentPresenter);

    void display();

    void dontDisplay();
}
