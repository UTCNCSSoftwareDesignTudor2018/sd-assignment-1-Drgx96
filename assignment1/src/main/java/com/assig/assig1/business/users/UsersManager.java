package com.assig.assig1.business.users;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.assig.assig1.DAO.UserDAOJdbc;
import com.assig.assig1.models.StudentInformation;
import com.assig.assig1.models.User;

public class UsersManager {

	private UserDAOJdbc usersDAO;

	public UsersManager()
	{
		usersDAO = new UserDAOJdbc();
	}
	public void updateUserInfo(String userId, String firstName, String lastName, String icn, String address) {
		User user = usersDAO.get(Integer.valueOf(userId));
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setIdentitycardnumber(icn);
	}

	public User getUser(String userId) {
		return usersDAO.get(Integer.valueOf(userId));
	}

	public StudentInformation getStudentInformation(String userId) {
		
		return null;
	}

	public String logIn(String username, String password) {
		User user = usersDAO.get(username);
		if (generateMD5(password).equals(user.getPasswordMD5()))
			return String.valueOf(user.getId());
		else
			return null;
	}

	public boolean isProffesor(String userId) {
		// TODO Auto-generated method stub
		return false;
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
