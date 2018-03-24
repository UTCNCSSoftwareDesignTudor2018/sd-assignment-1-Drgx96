package com.assig.assig1.presenters;

import com.assig.assig1.IFacade;
import com.assig.assig1.IStudentInformationView;
import com.assig.assig1.IStudentView;
import com.assig.assig1.IUserAccountInformationView;
import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;
import com.assig.assig1.userinterface.student.IStudentInformationPresenter;
import com.assig.assig1.userinterface.student.IStudentPresenter;
import com.assig.assig1.userinterface.user.IUserAccountInformationPresenter;

public class StudentPresenter implements IStudentInformationPresenter, IStudentPresenter, IUserAccountInformationPresenter{

	private IFacade facade;
	private String userId;
	private IStudentInformationView studentInfoView;
	private IStudentView studentView;
	private IUserAccountInformationView userAccountInfoView;
	
	public StudentPresenter(IFacade facade, IStudentInformationView studentInfoView, IStudentView studentView, IUserAccountInformationView userAccountInfoView, String userId) {
		this.facade=facade;
		this.userId=userId;
		this.studentInfoView = studentInfoView;
		this.studentView = studentView;
		this.userAccountInfoView = userAccountInfoView;
		studentView.display();
	}
	
	public void saveAccountInformation(String firstName, String lastName, String icn, String address) {
		//TO BE VALIDATED
		facade.saveAccountInformationForUser(userId, firstName, lastName, icn, address);
	}

	public String getUsername() {
		return facade.getUsernameForUser(userId);
	}

	public void presentAccountInformation() {
		User user = facade.getUser(userId);
		userAccountInfoView.setFirstName(user.getFirstName());
		userAccountInfoView.setLastName(user.getLastName());
		userAccountInfoView.setICN(user.getICN());
		userAccountInfoView.setAddress(user.getAddress());
		userAccountInfoView.setPresenter(this);
		userAccountInfoView.show();
	}

	public void logOut() {
		// TODO Auto-generated method stub
	}

	public void presentStudentInformation() {
		StudentInformation studentInformation = facade.getStudentInformation(userId);
		studentInfoView.setIdentificationNumber(studentInformation.getIdentificationNumber());
		studentInfoView.setGroup(studentInformation.getGroupNumber());
		studentInfoView.setEnrollments(studentInformation.getEnrollments());
		studentInfoView.setPresenter(this);
		studentInfoView.display();
	}

	public void leaveCourse(String courseId) {
		facade.withDrawUserFromCourse(userId, courseId);
	}
}
