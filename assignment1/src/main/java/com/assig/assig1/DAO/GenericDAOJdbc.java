package com.assig.assig1.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericDAOJdbc<T> implements GenericDAO<T> {
	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDAOJdbc() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public T get(Integer id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		String query = "SELECT * FROM `" + type.getSimpleName() + "` where " + type.getDeclaredFields()[0].getName()
				+ "= ?";
		try {
			findStatement = dbConnection.prepareStatement(query);
			findStatement.setInt(1, id);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				int n = type.getDeclaredFields().length;
				Object[] args = new Object[n];
				@SuppressWarnings("rawtypes")
				Class[] classes = new Class[n];
				int i = 0;
				for (Field field : type.getDeclaredFields()) {
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
					classes[i++] = field.getType();
				}
				try {
					return type.getConstructor(classes).newInstance(args);
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

	public Map<Integer, T> list() {
		Map<Integer, T> customers = new HashMap<Integer, T>();
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement findStatement = null;
		ResultSet rs = null;
		String query = "SELECT * FROM `" + type.getSimpleName().toLowerCase()+"s" + "` ;";
		try {
			findStatement = dbConnection.createStatement();
			rs = findStatement.executeQuery(query);
			while (rs.next()) {
				int n = type.getDeclaredFields().length;
				Object[] args = new Object[n];
				@SuppressWarnings("rawtypes")
				Class[] classes = new Class[n];
				int i = 0;
				for (Field field : type.getDeclaredFields()) {
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
					classes[i++] = field.getType();
				}
				try {
					customers.put(rs.getInt(1), type.getConstructor(classes).newInstance(args));
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
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public void add(T t) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		String query = "INSERT INTO `" + type.getSimpleName() + "` (";
		for (Field field : type.getDeclaredFields()) {
			query = query + field.getName() + ", ";
		}
		query = query.substring(0, query.length() - 2) + ") VALUES (?";
		int n = type.getDeclaredFields().length;
		for (int i = 0; i < n - 1; i++)
			query = query + ",?";
		query = query + ");";
		try {
			findStatement = dbConnection.prepareStatement(query);
			int i = 1;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(t);
					findStatement.setObject(i++, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			findStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public void update(T t) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		String query = "UPDATE `" + type.getSimpleName() + "` SET ";
		Field[] fs = type.getDeclaredFields();
		for (int i = 1; i < fs.length; i++)
			query = query + fs[i].getName() + "=?,";
		query = query.substring(0, query.length() - 1) + " WHERE " + fs[0].getName() + "=?";
		try {
			findStatement = dbConnection.prepareStatement(query);
			for (int i = 1; i < fs.length; i++) {
				fs[i].setAccessible(true);
				Object value;
				try {
					value = fs[i].get(t);
					findStatement.setObject(i, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			fs[0].setAccessible(true);
			findStatement.setObject(fs.length, fs[0].get(t));
			findStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public void delete(Integer id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		String query = "DELETE FROM " + type.getSimpleName() + " where " + type.getDeclaredFields()[0].getName()
				+ "=?;";
		try {
			findStatement = dbConnection.prepareStatement(query);
			findStatement.setLong(1, id);
			findStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

}
