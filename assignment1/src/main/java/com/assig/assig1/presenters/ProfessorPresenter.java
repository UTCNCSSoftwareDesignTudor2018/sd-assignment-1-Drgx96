package com.assig.assig1.presenters;

import com.assig.assig1.IFacade;
import com.assig.assig1.IProfessorView;
import com.assig.assig1.IUserAccountInformationView;
import com.assig.assig1.models.User;
import com.assig.assig1.userinterface.professor.IProfessorPresenter;
import com.assig.assig1.userinterface.user.IUserAccountInformationPresenter;

public class ProfessorPresenter implements IProfessorPresenter, IUserAccountInformationPresenter {

	private IFacade facade;
	private String userId;
	private IProfessorView professorView;
	private IUserAccountInformationView userAccountInfoView;

	public ProfessorPresenter(IFacade facade, IProfessorView professorView, IUserAccountInformationView userAccountInfoView, String userId) {
		this.facade=facade;
		this.userId=userId;
		this.professorView = professorView;
		this.userAccountInfoView = userAccountInfoView;
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

	public void presentStudentsInformation() {
		professorView.showStudents();
	}

	public void logOut() {
		// TODO Auto-generated method stub
		
	}

	public void presentReportsInformation() {
		// TODO Auto-generated method stub
		
	}

}
