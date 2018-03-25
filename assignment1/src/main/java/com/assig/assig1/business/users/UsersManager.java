package com.assig.assig1.business.users;

import java.util.List;

import com.assig.assig1.DAO.EnrollmentDAOJdbc;
import com.assig.assig1.DAO.GroupDAOJdbc;
import com.assig.assig1.DAO.UserDAOJdbc;
import com.assig.assig1.business.classes.ClassesManager;
import com.assig.assig1.models.Group;
import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;
import com.assig.assig1.models.Class;

public class UsersManager {

	private UserDAOJdbc usersDAO;
	private GroupDAOJdbc groupDAO;
	private EnrollmentDAOJdbc enrollmentDAOJdbc;
	private ClassesManager classesMgr;

	public UsersManager(ClassesManager classesMgr)
	{
		this.classesMgr = classesMgr;
		usersDAO = new UserDAOJdbc();
		groupDAO = new GroupDAOJdbc();
		enrollmentDAOJdbc = new EnrollmentDAOJdbc();
	}
	public void updateUserInfo(String userId, String firstName, String lastName, String icn, String address) {
		User user = usersDAO.get(Integer.valueOf(userId));
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setIdentitycardnumber(icn);
		usersDAO.update(user);
	}

	public User getUser(String userId) {
		return usersDAO.get(Integer.valueOf(userId));
	}

	public StudentInformation getStudentInformation(String userId) {
		User user = usersDAO.get(Integer.valueOf(userId));
		Group group = groupDAO.get(user.getGroup_id());
		List<Class> enrollments = classesMgr.getClassesOfUser(user.getId());
		return new StudentInformation(userId, group.getGroup_code(), enrollments);
	}

	public String logIn(String username, String password) {
		User user = usersDAO.getByUsername(username);
		if (user!= null && user.getPasswordMD5().equals(generateMD5(password)))
			return String.valueOf(user.getId());
		else
			return null;
	}

	public boolean isProffesor(String userId) {
		User user = usersDAO.get(Integer.valueOf(userId));
		return user.getAdmin();
	}
	
	private String generateMD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
	}
}
