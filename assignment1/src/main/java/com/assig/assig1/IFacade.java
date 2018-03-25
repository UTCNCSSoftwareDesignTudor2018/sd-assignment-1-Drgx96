package com.assig.assig1;

import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.assig.assig1.models.Class;

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

	void withDrawUserFromCourses(int userId, List<Integer> coursesIds);

}
