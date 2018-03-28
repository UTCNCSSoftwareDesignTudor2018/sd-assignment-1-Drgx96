package com.assig.assig1.DAO;

import com.assig.assig1.models.Enrollment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EnrollmentDAOJdbc extends GenericDAOJdbc<Enrollment> {

    public LinkedList<Enrollment> getByUserId(int id) {
        LinkedList<Enrollment> enrollments = new LinkedList<Enrollment>();
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement findStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM `" + Enrollment.class.getSimpleName().toLowerCase() + "s" + "` WHERE users_id=" + String.valueOf(id) + ";";
        try {
            findStatement = dbConnection.createStatement();
            rs = findStatement.executeQuery(query);
            while (rs.next()) {
                int n = Enrollment.class.getDeclaredFields().length;
                Object[] args = new Object[n];
                @SuppressWarnings("rawtypes")
                Class[] classes = new Class[n];
                int i = 0;
                for (Field field : Enrollment.class.getDeclaredFields()) {
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
                    enrollments.add(Enrollment.class.getConstructor(classes).newInstance(args));
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            ConnectionFactory.close(rs);
            return enrollments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void delete(List<Enrollment> enrollments) {
        if (enrollments == null || enrollments.size() < 1)
            return;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        StringBuilder queryBuilder = new StringBuilder("DELETE FROM " + Enrollment.class.getSimpleName().toLowerCase() + "s" + " where ");
        for (int i = 0; i < enrollments.size(); i++) {
            queryBuilder.append(Enrollment.class.getDeclaredFields()[0].getName()).append("=? AND ").append(Enrollment.class.getDeclaredFields()[1].getName()).append("=? OR ");
        }
        String query = queryBuilder.toString();
        query = query.substring(0, query.length() - 3) + ";";
        try {
            findStatement = dbConnection.prepareStatement(query);
            int i = 1;
            for (Enrollment e : enrollments) {
                findStatement.setInt(i++, e.getUserId());
                findStatement.setInt(i++, e.getClassId());
            }
            findStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
