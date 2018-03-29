package com.assig.assig1.business.classes;

import com.assig.assig1.DAO.ClassDAOJdbc;
import com.assig.assig1.DAO.EnrollmentDAOJdbc;
import com.assig.assig1.DAO.GradeDAOJdbc;
import com.assig.assig1.models.Class;
import com.assig.assig1.models.Enrollment;
import com.assig.assig1.models.Grade;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassesManager {

    private ClassDAOJdbc classDAO;
    private EnrollmentDAOJdbc enrollmentDAO;
    private GradeDAOJdbc gradesDAO;

    public ClassesManager() {
        classDAO = new ClassDAOJdbc();
        enrollmentDAO = new EnrollmentDAOJdbc();
        gradesDAO = new GradeDAOJdbc();
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
        List<Integer> joinedClassesIds = enrollmentDAO.getByUserId(userId).stream().map(x -> x.getClassId()).collect(Collectors.toList());
        return getClasses().stream().filter(p -> !joinedClassesIds.contains(p.getId())).collect(Collectors.toList());
}

    public void enrollStudentToClass(int userId, int classId) {
        Enrollment enrollment = new Enrollment(userId, classId);
        enrollmentDAO.add(enrollment, false);
    }

    public List<Class> getClassesOfUser(int id) {
        return classDAO.listClassesOfUser(id);
    }

    public boolean withdrawUserFromCourses(int userId, List<Integer> coursesIds) {
		return enrollmentDAO.delete(coursesIds.stream().map(x -> new Enrollment(userId, x)).collect(Collectors.toList()));
    }

    public List<Grade> getGradesForEnrollment(Enrollment enrollment) {
        return gradesDAO.getGradesForEnrollment(enrollment);
    }

    public void addGradeToEnrollment(Enrollment enrollment, Float grade, String examination) {
        gradesDAO.add(new Grade(0,grade, new Timestamp(System.currentTimeMillis()), examination, enrollment.getUserId(), enrollment.getClassId()), true);
    }

    public List<Enrollment> getEnrollments() {
        return new LinkedList<>(enrollmentDAO.list().values());
    }
}
