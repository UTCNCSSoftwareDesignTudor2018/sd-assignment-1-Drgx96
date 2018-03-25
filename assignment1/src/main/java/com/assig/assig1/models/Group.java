package com.assig.assig1.models;

public class Group {
    int id;
    String group_code;
    int representing_student;

    public Group(int id, String group_code, int representing_student) {
        super();
        this.id = id;
        this.group_code = group_code;
        this.representing_student = representing_student;
    }

    public int getId() {
        return id;
    }

    public String getGroup_code() {
        return group_code;
    }

    public int getRepresenting_student() {
        return representing_student;
    }
}
