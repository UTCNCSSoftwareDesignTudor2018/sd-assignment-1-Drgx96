package com.assig.assig1.presenters;

import com.assig.assig1.Facade;
import com.assig.assig1.IFacade;
import com.assig.assig1.userinterface.professor.ProfessorView;
import com.assig.assig1.userinterface.professor.StudentInformationForProfessorView;
import com.assig.assig1.userinterface.student.StudentInformationView;
import com.assig.assig1.userinterface.student.StudentView;
import com.assig.assig1.userinterface.user.ILoginPresenter;
import com.assig.assig1.userinterface.user.LogInView;
import com.assig.assig1.userinterface.user.UserAccountInformationView;

public class LoginPresenter implements ILoginPresenter {

    private IFacade facade;
    private ILoginView loginView;
    @SuppressWarnings("unused")
	private StudentPresenter studentPresenter;
    @SuppressWarnings("unused")
	private ProfessorPresenter professorPresenter;

    public LoginPresenter(IFacade facade, ILoginView loginView) {
        this.facade = facade;
        this.loginView = loginView;
        loginView.setPresenter(this);
        loginView.display();
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
		LoginPresenter loginPresenter = new LoginPresenter(new Facade(), new LogInView());
    }

    public void logIn(String username, String password) {
        String userId = facade.logIn(username, password);
        if (userId != null) {
            loginView.dontDisplay();
            if (facade.isProffesor(userId)) {
                professorPresenter = new ProfessorPresenter(facade, this, new ProfessorView(), new UserAccountInformationView(),  new StudentInformationForProfessorView(), userId);
            } else {
                studentPresenter = new StudentPresenter(facade, this, new StudentInformationView(), new StudentView(), new UserAccountInformationView(), userId);
            }
        } else {
            loginView.showInvalidCredentials();
        }
    }

    public void show() {
        loginView.display();
    }
}
