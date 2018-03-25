package com.assig.assig1.business.users;

import com.assig.assig1.DAO.*;
import com.assig.assig1.business.classes.ClassesManager;
import com.assig.assig1.models.*;
import com.assig.assig1.models.Class;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersManager {

    private ChosenAddressDAOJdbc choosenAddressDAO;
    private UserDAOJdbc usersDAO;
    private GroupDAOJdbc groupDAO;
    private ClassesManager classesMgr;
    private AddressDAOJdbc addressDAO;

    public UsersManager(ClassesManager classesMgr) {
        this.classesMgr = classesMgr;
        usersDAO = new UserDAOJdbc();
        groupDAO = new GroupDAOJdbc();
        addressDAO = new AddressDAOJdbc();
        choosenAddressDAO = new ChosenAddressDAOJdbc();
    }

    public void updateUserInfo(String userId, String firstName, String lastName, String icn, String address) {
        User user = usersDAO.get(Integer.valueOf(userId));
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setIdentitycardnumber(icn);
        usersDAO.update(user);
    }

    public User getUser(String userId) {
        return usersDAO.get(Integer.valueOf(userId));
    }

    public StudentInformation getStudentInformation(String userId) {
        User user = usersDAO.get(Integer.valueOf(userId));
        Group group = groupDAO.get(user.getGroup_id());
        List<Class> enrollments = classesMgr.getClassesOfUser(user.getId());
        return new StudentInformation(user, group.getGroup_code(), enrollments);
    }

    public String logIn(String username, String password) {
        User user = usersDAO.getByUsername(username);
        if (user != null && user.getPasswordMD5().equals(generateMD5(password)))
            return String.valueOf(user.getId());
        else
            return null;
    }

    public boolean isProffesor(String userId) {
        User user = usersDAO.get(Integer.valueOf(userId));
        return user.getAdmin();
    }

    private String generateMD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public void setUsersAddress(int userId, Address address) {
        int id = addressDAO.add(address);
        choosenAddressDAO.add(new ChosenAddress(userId, id));
    }

    public List<StudentInformation> getStudentsInformation() {
        List<User> students = usersDAO.list().values().stream().filter(x -> !x.getAdmin()).collect(Collectors.toList());
        List<Group> groups = new LinkedList<>(groupDAO.list().values());
        LinkedList<StudentInformation> studentsInformations = new LinkedList<>();
        for(User s: students)
        {
            studentsInformations.add(new StudentInformation(s, groups.stream().filter(x -> x.getId() == s.getGroup_id()).findFirst().get().getGroup_code(), classesMgr.getClassesOfUser(s.getId())));
        }
        return studentsInformations;
    }
}
