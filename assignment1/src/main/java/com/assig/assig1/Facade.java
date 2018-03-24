package com.assig.assig1;

import com.assig.assig1.business.classes.ClassesManager;
import com.assig.assig1.business.users.UsersManager;
import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;

public class Facade implements IFacade {

	private UsersManager usersMgr;
	private ClassesManager classesMgr;

	public Facade()
	{
		usersMgr = new UsersManager();
		classesMgr = new ClassesManager();
	}
	
	public void saveAccountInformationForUser(String userId, String firstName, String lastName, String icn,
			String address) {
		usersMgr.updateUserInfo(userId, firstName, lastName, icn, address);
	}

	public String getUsernameForUser(String userId) {
		return usersMgr.getUser(userId).getUsername();
	}

	public User getUser(String userId) {
		return usersMgr.getUser(userId);
	}

	public StudentInformation getStudentInformation(String userId) {
		return usersMgr.getStudentInformation(userId);
	}

	public void withDrawUserFromCourse(String userId, String courseId) {
		classesMgr.withDrawUserFromCourse(userId, courseId);
	}

	public String logIn(String username, String password) {
		return usersMgr.logIn(username, password);
	}

	public boolean isProffesor(String userId) {
		return usersMgr.isProffesor(userId);
	}
}
