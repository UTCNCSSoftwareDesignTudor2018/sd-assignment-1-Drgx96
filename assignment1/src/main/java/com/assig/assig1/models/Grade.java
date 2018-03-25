package com.assig.assig1.models;

import java.sql.Timestamp;

public class Grade {
    int id;
    double value;
    Timestamp date;
    String evaluation_type;
    int enrollment_users_id;
    int enrollment_class_id;

    public Grade(int id, double value, Timestamp date, String evaluation_type, int enrollment_users_id, int enrollment_class_id) {
        super();
        this.id = id;
        this.value = value;
        this.date = date;
        this.evaluation_type = evaluation_type;
        this.enrollment_users_id = enrollment_users_id;
        this.enrollment_class_id = enrollment_class_id;
    }
}
