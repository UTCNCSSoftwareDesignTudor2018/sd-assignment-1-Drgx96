package com.assig.assig1.models;

import java.util.List;

public class StudentInformation {
    User user;
    String groupNumber;
    List<Class> classes;

    public StudentInformation(User user, String groupNumber, List<Class> classes) {
        this.user = user;
        this.groupNumber = groupNumber;
        this.classes = classes;
    }

    public String getIdentificationNumber() {
        return String.valueOf(user.getId());
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public User getUser()
    {
        return user;
    }
}
