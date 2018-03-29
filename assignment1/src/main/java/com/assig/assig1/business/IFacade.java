package com.assig.assig1.business;

import com.assig.assig1.models.*;
import com.assig.assig1.models.Class;

import java.util.List;

public interface IFacade {

    void saveAccountInformationForUser(String userId, String firstName, String lastName, String icn, String address);

    String getUsernameForUser(String userId);

    User getUser(String userId);

    StudentInformation getStudentInformation(String userId);

    void withDrawUserFromCourse(int userId, int courseId);

    String logIn(String username, String password);

    boolean isProffesor(String userId);

    Class getClass(int classId);

    List<Class> getAvaiableClassesForUser(int userId);

    void enrollUserToClass(int userId, int classId);

    boolean withDrawUserFromCourses(int userId, List<Integer> coursesIds);

    void setAddressForUser(int userId, Address address);

    List<StudentInformation> getStudents();

    List<Grade> getGradesForEnrollment(Enrollment enrollment);

    void addGradeToEnrollment(Enrollment enrollment, Float grade, String examination);
}
