package com.assig.assig1.business.classes;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.assig.assig1.DAO.ClassDAOJdbc;
import com.assig.assig1.DAO.EnrollmentDAOJdbc;
import com.assig.assig1.models.Enrollment;
import com.assig.assig1.models.Class;

public class ClassesManager {

	private ClassDAOJdbc classDAO;
	private EnrollmentDAOJdbc enrollmentDAO;

	public ClassesManager()
	{
		classDAO = new ClassDAOJdbc();
		enrollmentDAO = new EnrollmentDAOJdbc();
	}
	
	public void withDrawUserFromCourse(int userId, int courseId) {
		LinkedList<Enrollment> enrollment = new LinkedList<Enrollment>();
		enrollment.add(new Enrollment(userId, courseId));
		enrollmentDAO.delete(enrollment);
	}
	
	public Class getClass(int classId) {
		return classDAO.get(classId);
	}

	public List<Class> getClasses() {
		return new LinkedList<Class>(classDAO.list().values());
	}

	public List<Class> getAvaiableClassesForUser(int userId) {
		List<Integer> joinedClassesIds = enrollmentDAO.getByUserId(userId).stream().map(x -> x.getUserId()).collect(Collectors.toList());
		return getClasses().stream().filter(p -> !joinedClassesIds.contains(p.getId())).collect(Collectors.toList());
	}

	public void enrollStudentToClass(int userId, int classId) {
		Enrollment enrollment = new Enrollment(userId, classId);
		enrollmentDAO.add(enrollment);
	}

	public List<Class> getClassesOfUser(int id) {
		return classDAO.listClassesOfUser(id);
	}

	public void withdrawUserFromCourses(int userId, List<Integer> coursesIds) {
		enrollmentDAO.delete(coursesIds.stream().map(x -> new Enrollment(userId, x)).collect(Collectors.toList()));
	}
}
