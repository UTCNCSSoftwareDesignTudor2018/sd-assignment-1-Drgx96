package com.assig.assig1.presenters;

import com.assig.assig1.business.IFacade;
import com.assig.assig1.models.*;
import com.assig.assig1.models.Class;
import com.assig.assig1.userinterface.professor.IProfessorPresenter;
import com.assig.assig1.userinterface.user.ILoginPresenter;
import com.assig.assig1.userinterface.user.IUserAccountInformationPresenter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessorPresenter implements IProfessorPresenter, IUserAccountInformationPresenter {

    private IStudentInfoForProfessorView studentInfoForProfessorView;
    private IFacade facade;
    private String userId;
    private IProfessorView professorView;
    private IUserAccountInformationView userAccountInfoView;
    private ILoginPresenter loginPresenter;
    private List<StudentInformation> studentInformations;
    private StudentInformation currentStudent;
    private Class currentClass;

    public ProfessorPresenter(IFacade facade, ILoginPresenter loginPresenter, IProfessorView professorView, IUserAccountInformationView userAccountInfoView, IStudentInfoForProfessorView studentInfoForProfessorView, String userId) {
        this.facade = facade;
        this.loginPresenter = loginPresenter;
        this.userId = userId;
        this.professorView = professorView;
        professorView.setPresenter(this);
        this.userAccountInfoView = userAccountInfoView;
        userAccountInfoView.setPresenter(this);
        this.studentInfoForProfessorView = studentInfoForProfessorView;
        studentInfoForProfessorView.setPresenter(this);
        professorView.display();
    }

    @Override
    public void saveAccountInformation(String firstName, String lastName, String icn, String address) {
        //TO BE VALIDATED
        facade.saveAccountInformationForUser(userId, firstName, lastName, icn, address);
    }

    @Override
    public String getUsername() {
        return facade.getUsernameForUser(userId);
    }

    @Override
    public void presentAccountInformation() {
        User user = facade.getUser(userId);
        userAccountInfoView.setFirstName(user.getFirstName());
        userAccountInfoView.setLastName(user.getLastName());
        userAccountInfoView.setICN(user.getICN());
        userAccountInfoView.setPresenter(this);
        userAccountInfoView.show();
    }

    @Override
    public void logOut() {
        userAccountInfoView.dontDisplay();
        professorView.dontDisplay();
        studentInfoForProfessorView.dontDisplay();
        loginPresenter.show();
    }

    @Override
    public void setAddress(String country, String city, String street, String number, String buildingNumber,
                           String floor, String doorNumber) {
        Address address = new Address(0, country, city, street, number, buildingNumber, floor, doorNumber);
        facade.setAddressForUser(Integer.valueOf(userId), address);
    }

    @Override
    public List<String[]> getStudents() {
        studentInformations = facade.getStudents();
        LinkedList<String[]> studsInfo = new LinkedList<>();
        for (StudentInformation s : studentInformations) {
            User u = s.getUser();
            studsInfo.add(new String[]{u.getFirstName(), u.getLastName(), String.valueOf(u.getId()), s.getGroupNumber(), u.getPhone(), u.getEmail()});
        }
        studentInfoForProfessorView.dontDisplay();
        return studsInfo;
    }

    @Override
    public void presentInfoForStudentAtIndex(int row) {
        currentStudent = studentInformations.get(row);
        studentInfoForProfessorView.setStudentName(currentStudent.getUser().getFirstName() + " " + currentStudent.getUser().getLastName());
        studentInfoForProfessorView.setGroup(currentStudent.getGroupNumber());
        studentInfoForProfessorView.setIdentificationNumber(currentStudent.getIdentificationNumber());
        studentInfoForProfessorView.setJoinedCourses(currentStudent.getClasses().stream().map(x -> x.getSubject()).collect(Collectors.toList()));
        studentInfoForProfessorView.display();
    }

    @Override
    public void presentReportsInformation() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<String[]> getGradesForClassAtIndex(int row) {
        currentClass = currentStudent.getClasses().get(row);
        List<Grade> gs = facade.getGradesForEnrollment(new Enrollment(currentStudent.getUser().getId(), currentStudent.getClasses().get(row).getId()));
        List<String[]> grades = new LinkedList<String[]>();
        for (Grade g : gs) {
            grades.add(new String[]{String.valueOf(g.getGrade()), g.getExaminationType()});
        }
        return grades;
    }

    @Override
    public void addGrade(String grade, String examination) {
        facade.addGradeToEnrollment(new Enrollment(currentStudent.getUser().getId(), currentClass.getId()),  Float.valueOf(grade), examination);
        studentInfoForProfessorView.showGradesForClassAtIndex(currentStudent.getClasses().indexOf(currentClass));
    }
}
