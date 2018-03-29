package com.assig.assig1.DAO;

import com.assig.assig1.models.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOJdbc extends GenericDAOJdbc<User> {

    public User getByUsername(String userName) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM `users` where username = ?";
        try {
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setString(1, userName);
            rs = findStatement.executeQuery();
            if (rs.next()) {
                int n = User.class.getDeclaredFields().length;
                Object[] args = new Object[n];
                @SuppressWarnings("rawtypes")
                Class[] classes = new Class[n];
                int i = 0;
                for (Field field : User.class.getDeclaredFields()) {
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
                    return User.class.getConstructor(classes).newInstance(args);
                } catch (InstantiationException e) {
                    //e.printStackTrace();
                    return null;
                } catch (IllegalAccessException e) {
                    //e.printStackTrace();
                    return null;
                } catch (IllegalArgumentException e) {
                    //e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e) {
                    //e.printStackTrace();
                    return null;
                } catch (NoSuchMethodException e) {
                    //e.printStackTrace();
                    return null;
                } catch (SecurityException e) {
                    //e.printStackTrace();
                    return null;
                }
            } else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
