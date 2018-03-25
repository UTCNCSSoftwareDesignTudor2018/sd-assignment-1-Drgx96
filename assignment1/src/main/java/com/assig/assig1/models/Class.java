package com.assig.assig1.models;

public class Class {
    int id;
    String subject;

    public Class(int id, String subject) {
        super();
        this.id = id;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return subject;
    }
}
