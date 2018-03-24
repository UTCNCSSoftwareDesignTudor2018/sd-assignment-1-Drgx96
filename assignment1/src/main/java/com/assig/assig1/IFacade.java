package com.assig.assig1;

import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;

public interface IFacade {

	void saveAccountInformationForUser(String userId, String firstName, String lastName, String icn, String address);

	String getUsernameForUser(String userId);

	User getUser(String userId);

	StudentInformation getStudentInformation(String userId);

	void withDrawUserFromCourse(String userId, String courseId);

	String logIn(String username, String password);

	boolean isProffesor(String userId);

}
