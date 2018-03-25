package com.assig.assig1.presenters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.assig.assig1.IFacade;
import com.assig.assig1.IStudentInformationView;
import com.assig.assig1.IStudentView;
import com.assig.assig1.IUserAccountInformationView;
import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;
import com.assig.assig1.models.Class;
import com.assig.assig1.userinterface.student.IStudentInformationPresenter;
import com.assig.assig1.userinterface.student.IStudentPresenter;
import com.assig.assig1.userinterface.user.ILoginPresenter;
import com.assig.assig1.userinterface.user.IUserAccountInformationPresenter;

public class StudentPresenter implements IStudentInformationPresenter, IStudentPresenter, IUserAccountInformationPresenter{

	private IFacade facade;
	private String userId;
	private IStudentInformationView studentInfoView;
	private IStudentView studentView;
	private IUserAccountInformationView userAccountInfoView;
	private ILoginPresenter loginPresenter;
	private List<Class> avaiableClasses;
	private StudentInformation studentInformation;
	
	public StudentPresenter(IFacade facade, ILoginPresenter loginPresenter, IStudentInformationView studentInfoView, IStudentView studentView, IUserAccountInformationView userAccountInfoView, String userId) {
		this.facade=facade;
		this.loginPresenter = loginPresenter;
		this.userId=userId;
		this.studentInfoView = studentInfoView;
		studentInfoView.setPresenter(this);
		this.studentView = studentView;
		studentView.setPresenter(this);
		this.userAccountInfoView = userAccountInfoView;
		userAccountInfoView.setPresenter(this);
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
		studentView.dontDisplay();
		loginPresenter.show();
	}

	public void presentStudentInformation() {
		studentInformation = facade.getStudentInformation(userId);
		studentInfoView.setIdentificationNumber(studentInformation.getIdentificationNumber());
		studentInfoView.setGroup(studentInformation.getGroupNumber());
		studentInfoView.showEnrollments(studentInformation.getClasses().stream().map(x -> x.getSubject()).collect(Collectors.toList()));
		studentInfoView.display();
	}
	
	public List<String> getAvailableClasses() {
		avaiableClasses = facade.getAvaiableClassesForUser(Integer.valueOf(userId));
		return (avaiableClasses).stream().map(n -> ((Class) n).getSubject()).collect(Collectors.toList());
	}

	public void joinClassAtIndex(int index) {
		facade.enrollUserToClass(Integer.valueOf(userId), avaiableClasses.get(index).getId());
		studentInfoView.showEnrollments(studentInformation.getClasses().stream().map(x -> x.getSubject()).collect(Collectors.toList()));
	}

	public void leaveCoursesWithIndexes(Integer[] selectedRows) { 
		facade.withDrawUserFromCourses(Integer.valueOf(userId), Arrays.asList(selectedRows).stream().map(x -> studentInformation.getClasses().get(x).getId()).collect(Collectors.toList()));
		studentInformation = facade.getStudentInformation(userId);
		studentInfoView.showEnrollments(studentInformation.getClasses().stream().map(x -> x.getSubject()).collect(Collectors.toList()));
	}
}
