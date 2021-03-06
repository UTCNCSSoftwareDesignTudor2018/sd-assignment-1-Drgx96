package com.assig.assig1.userinterface.student;

import java.util.List;

public interface IStudentInformationPresenter {

    List<String> getAvailableClasses();

    void joinClassAtIndex(int index);

    boolean leaveCoursesWithIndexes(Integer[] selectedRows);

    List<String[]> getGradesForClassAtIndex(int minSelectionIndex);

    String getStudent();
}
