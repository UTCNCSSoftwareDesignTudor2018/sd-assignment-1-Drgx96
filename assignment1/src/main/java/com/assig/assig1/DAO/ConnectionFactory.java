package com.assig.assig1.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;

import com.assig.assig1.models.User;

public class ConnectionFactory {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11227163?useSSL=false";
	private static final String USER = "sql11227163";
	private static final String PASS = "tWzhK9yqpa";
	private static ConnectionFactory singleInstance = new ConnectionFactory();

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		ConnectionFactory.getConnection();
		UserDAOJdbc userDAO = new UserDAOJdbc();
		AddressDAOJdbc addressDAO = new AddressDAOJdbc();
		ChosenAddressDAOJdbc chosenAddressDAO = new ChosenAddressDAOJdbc();
		ClassDAOJdbc classDAO = new ClassDAOJdbc();
		EnrollmentDAOJdbc enrollmentDAO = new EnrollmentDAOJdbc();
		GroupDAOJdbc groupDAO = new GroupDAOJdbc();
		GradeDAOJdbc gradesDAO = new GradeDAOJdbc();
		userDAO.list();
		addressDAO.list();
		chosenAddressDAO.list();
		classDAO.list();
		enrollmentDAO.list();
		groupDAO.list();
		gradesDAO.list();
	}
}
