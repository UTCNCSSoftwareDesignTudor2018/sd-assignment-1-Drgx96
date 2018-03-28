package com.assig.assig1.userinterface.professor;

import java.util.List;

public interface IProfessorPresenter {

    String getUsername();

    void presentAccountInformation();

    void logOut();

    void presentReportsInformation();

    List<String[]> getStudents();

    void presentInfoForStudentAtIndex(int row);

	List<String[]> getGradesForClassAtIndex(int row);

    void addGrade(String grade, String examination);
}
