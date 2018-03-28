package com.assig.assig1.DAO;

import com.assig.assig1.models.Enrollment;
import com.assig.assig1.models.Grade;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GradeDAOJdbc extends GenericDAOJdbc<Grade> {

    public List<Grade> getGradesForEnrollment(Enrollment enrollment) {
        List<Grade> grades = new LinkedList<Grade>();
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement findStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM `" + Grade.class.getSimpleName().toLowerCase() + "s`" + " WHERE "
                + "enrollment_users_id="+ String.valueOf(enrollment.getUserId())+" AND enrollment_class_id="+String.valueOf(enrollment.getClassId())+";";
        try {
            findStatement = dbConnection.createStatement();
            rs = findStatement.executeQuery(query);
            while (rs.next()) {
                int n = Grade.class.getDeclaredFields().length;
                Object[] args = new Object[n];
                @SuppressWarnings("rawtypes")
                Class[] classes = new Class[n];
                int i = 0;
                for (Field field : Grade.class.getDeclaredFields()) {
                    if (field.getType() == Boolean.class) {
                        args[i] = ((Integer) rs.getObject(field.getName())) > 0;
                    } else {
                        Object o = rs.getObject(field.getName());
                        if ((field.getType() == Integer.class || field.getType() == int.class) && o == null) {
                            args[i] = 0;
                        } else {
                            args[i] = o;
                        }
                    }
                    classes[i++] = field.getType();
                }
                try {
                    grades.add(Grade.class.getConstructor(classes).newInstance(args));
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    return null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return null;
                } catch (SecurityException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            ConnectionFactory.close(rs);
            return grades;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
