package com.assig.assig1;

import com.assig.assig1.business.classes.ClassesManager;
import com.assig.assig1.business.users.UsersManager;
import com.assig.assig1.models.Address;
import com.assig.assig1.models.Class;
import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;

import java.util.List;

public class Facade implements IFacade {

    private UsersManager usersMgr;
    private ClassesManager classesMgr;

    public Facade() {
        classesMgr = new ClassesManager();
        usersMgr = new UsersManager(classesMgr);
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

    public void withDrawUserFromCourse(int userId, int courseId) {
        classesMgr.withDrawUserFromCourse(userId, courseId);
    }

    public String logIn(String username, String password) {
        return usersMgr.logIn(username, password);
    }

    public boolean isProffesor(String userId) {
        return usersMgr.isProffesor(userId);
    }

    public Class getClass(int classId) {
        return classesMgr.getClass(classId);
    }

    public List<Class> getAvaiableClassesForUser(int userId) {
        return classesMgr.getAvaiableClassesForUser(userId);
    }

    public void enrollUserToClass(int userId, int classId) {
        classesMgr.enrollStudentToClass(userId, classId);
    }

    public void withDrawUserFromCourses(int userId, List<Integer> coursesIds) {
        classesMgr.withdrawUserFromCourses(userId, coursesIds);
    }

    public void setAddressForUser(int userId, Address address) {
        usersMgr.setUsersAddress(userId, address);
    }

    public List<StudentInformation> getStudents() {
        return usersMgr.getStudentsInformation();
    }
}
