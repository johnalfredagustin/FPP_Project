package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.StudentDAL;
import orm.StudentObject;
import orm.ProfessorStudentCourseMetricObject;

public class StudentController {

	private StudentDAL studentDAL = new StudentDAL();

	public StudentObject getStudentDetail(int studentID) throws SQLException {

		return studentDAL.getStudentDetail(studentID);
	}

	public boolean signUp(StudentObject studentObj) throws SQLException {
		return studentDAL.signup(studentObj);
	}

	public ArrayList<ProfessorStudentCourseMetricObject> getAllCourse(int studentID) throws SQLException {
		return studentDAL.getAllCourse(studentID);
	}

	public int saveCourse(int studentID, int courseID, int professorID) throws SQLException {
		return studentDAL.saveCourse(studentID, courseID, professorID);
	}

	public ArrayList<ProfessorStudentCourseMetricObject> getAllgrades(int studentID) throws SQLException {
		return studentDAL.getAllgrades(studentID);
	}
	
}
