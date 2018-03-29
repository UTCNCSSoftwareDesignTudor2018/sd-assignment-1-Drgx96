package com.assig.assig1.presenters;

import com.assig.assig1.business.IFacade;
import com.assig.assig1.models.*;
import com.assig.assig1.models.Class;
import com.assig.assig1.userinterface.student.IStudentInformationPresenter;
import com.assig.assig1.userinterface.student.IStudentPresenter;
import com.assig.assig1.userinterface.user.ILoginPresenter;
import com.assig.assig1.userinterface.user.IUserAccountInformationPresenter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentPresenter implements IStudentInformationPresenter, IStudentPresenter, IUserAccountInformationPresenter {

    private IFacade facade;
    private String userId;
    private IStudentInformationView studentInfoView;
    private IStudentView studentView;
    private IUserAccountInformationView userAccountInfoView;
    private ILoginPresenter loginPresenter;
    private List<Class> avaiableClasses;
    private StudentInformation studentInformation;
    private List<Class> classes;
    private Class currentClass;

    public StudentPresenter(IFacade facade, ILoginPresenter loginPresenter, IStudentInformationView studentInfoView, IStudentView studentView, IUserAccountInformationView userAccountInfoView, String userId) {
        this.facade = facade;
        this.loginPresenter = loginPresenter;
        this.userId = userId;
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
        userAccountInfoView.setPresenter(this);
        userAccountInfoView.show();
    }

    public void logOut() {
        studentInfoView.dontDisplay();
        userAccountInfoView.dontDisplay();
        studentView.dontDisplay();
        loginPresenter.show();
    }

    public void presentStudentInformation() {
        studentInformation = facade.getStudentInformation(userId);
        studentInfoView.setIdentificationNumber(studentInformation.getIdentificationNumber());
        studentInfoView.setGroup(studentInformation.getGroupNumber());
        classes = studentInformation.getClasses();
        studentInfoView.showEnrollments(classes.stream().map(Class::getSubject).collect(Collectors.toList()));
        studentInfoView.display();
    }

    public List<String> getAvailableClasses() {
        avaiableClasses = facade.getAvaiableClassesForUser(Integer.valueOf(userId));
        return (avaiableClasses).stream().map(Class::getSubject).collect(Collectors.toList());
    }

    public void joinClassAtIndex(int index) {
        facade.enrollUserToClass(Integer.valueOf(userId), avaiableClasses.get(index).getId());
        studentInformation = facade.getStudentInformation(userId);
        studentInfoView.showEnrollments(studentInformation.getClasses().stream().map(Class::getSubject).collect(Collectors.toList()));
    }

    public boolean leaveCoursesWithIndexes(Integer[] selectedRows) {
        boolean inserted = facade.withDrawUserFromCourses(Integer.valueOf(userId), Arrays.stream(selectedRows).map(x -> studentInformation.getClasses().get(x).getId()).collect(Collectors.toList()));
        studentInformation = facade.getStudentInformation(userId);
        studentInfoView.showEnrollments(studentInformation.getClasses().stream().map(Class::getSubject).collect(Collectors.toList()));
		return inserted;
    }

    @Override
    public List<String[]> getGradesForClassAtIndex(int minSelectionIndex) {
        currentClass = studentInformation.getClasses().get(minSelectionIndex);
        List<Grade> gs = facade.getGradesForEnrollment(new Enrollment(studentInformation.getUser().getId(), studentInformation.getClasses().get(minSelectionIndex).getId()));
        List<String[]> grades = new LinkedList<String[]>();
        for (Grade g : gs) {
            grades.add(new String[]{String.valueOf(g.getGrade()), g.getExaminationType()});
        }
        return grades;
    }

    @Override
    public String getStudent() {
        return null;
    }

    @Override
    public void setAddress(String country, String city, String street, String number, String buildingNumber,
                           String floor, String doorNumber) {
        Address address = new Address(0, country, city, street, number, buildingNumber, floor, doorNumber);
        facade.setAddressForUser(Integer.valueOf(userId), address);
    }
}
