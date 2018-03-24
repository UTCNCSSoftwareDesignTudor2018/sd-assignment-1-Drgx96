package com.assig.assig1.presenters;

import com.assig.assig1.Facade;
import com.assig.assig1.IFacade;
import com.assig.assig1.ILoginView;
import com.assig.assig1.userinterface.professor.ProfessorView;
import com.assig.assig1.userinterface.student.StudentInformationView;
import com.assig.assig1.userinterface.student.StudentView;
import com.assig.assig1.userinterface.user.ILoginPresenter;
import com.assig.assig1.userinterface.user.LogInView;
import com.assig.assig1.userinterface.user.UserAccountInformationView;

public class LoginPresenter implements ILoginPresenter{

	private IFacade facade;
	private ILoginView loginView;
	private StudentPresenter studentPresenter;
	private ProfessorPresenter professorPresenter;

	public LoginPresenter(IFacade facade, ILoginView loginView)
	{
		this.facade = facade;
		this.loginView = loginView;
		loginView.setPresenter(this);
		loginView.display();
	}

	public void logIn(String username, String password) {
		String userId = facade.logIn(username, password);
		if(userId!= null)
		{
			loginView.dontDisplay();
			if(facade.isProffesor(userId))
			{
				professorPresenter = new ProfessorPresenter(facade, new ProfessorView(), new UserAccountInformationView(), userId);
			}
			else
			{
				studentPresenter = new StudentPresenter(facade, new StudentInformationView(), new StudentView(), new UserAccountInformationView(), userId);
			}
		}
		else
		{
			loginView.showInvalidCredentials();
		}
	}
	
	public static void main(String[] args)
	{
		LoginPresenter loginPresenter = new LoginPresenter(new Facade(), new LogInView());
	}
}
