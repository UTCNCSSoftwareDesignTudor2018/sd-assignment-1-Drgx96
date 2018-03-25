package com.assig.assig1.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.assig.assig1.models.Class;
import com.assig.assig1.models.Enrollment;

public class ClassDAOJdbc extends GenericDAOJdbc<Class> {

	public List<Class> listClassesOfUser(int userId) {
		LinkedList<Class> classes = new LinkedList<Class>();
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement findStatement = null;
		ResultSet rs = null;
		String query = "SELECT " + Class.class.getSimpleName().toLowerCase()+"s.* FROM " 
		+ Enrollment.class.getSimpleName().toLowerCase()+"s JOIN " +Class.class.getSimpleName().toLowerCase()
		+"s ON "+ Enrollment.class.getDeclaredFields()[1].getName() +"="+ Class.class.getDeclaredFields()[0].getName()
				+" WHERE "+Enrollment.class.getDeclaredFields()[0].getName()+"="+ String.valueOf(userId)+" ;";
		try {
			findStatement = dbConnection.createStatement();
			rs = findStatement.executeQuery(query);
			while (rs.next()) {
				int n = Class.class.getDeclaredFields().length;
				Object[] args = new Object[n];
				@SuppressWarnings("rawtypes")
				java.lang.Class[] cs = new java.lang.Class[n];
				int i = 0;
				for (Field field : Class.class.getDeclaredFields()) {
					if(field.getType() == Boolean.class)
					{
						args[i] = ((Integer)rs.getObject(field.getName())) > 0;
					}
					else
					{
						Object o = rs.getObject(field.getName());
						if((field.getType() == Integer.class || field.getType() == int.class)&& o == null)
						{
							args[i] = 0;
						}
						else
						{
							args[i] = o;
						}
					}
					cs[i++] = field.getType();
				}
				try {
					classes.add(Class.class.getConstructor(cs).newInstance(args));
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
			return classes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
}
