package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ProfessorDAL;
import orm.CourseObject;
import orm.ProfessorObject;
import orm.ProfessorStudentCourseMetricObject;

public class ProfessorController {

	private ProfessorDAL profDAL = new ProfessorDAL();

	public ProfessorObject getProfessorDetail(int professorID) throws SQLException {
		return profDAL.getProfessorDetail(professorID);
	}

	public ArrayList<ProfessorStudentCourseMetricObject> getAllStudentCourseDetail(int professorID)
			throws SQLException {
		return profDAL.getAllStudentCourseDetail(professorID);
	}

	public boolean submitOneStudentGrade(int professorID, int studentID, int courseID, double gradeNumber)
			throws SQLException {
		return profDAL.submitOneStudentGrade(professorID, studentID, courseID, gradeNumber);
	}

	public boolean submitAllStudentGrade(ArrayList<ProfessorStudentCourseMetricObject> profStudCourseList)
			throws SQLException {
		return profDAL.submitAllStudentGrade(profStudCourseList);
	}

	public boolean signUp(ProfessorObject professorObj) throws SQLException {
		return profDAL.signup(professorObj);
	}

	public ArrayList<CourseObject> getAllCourse() throws SQLException {
		return profDAL.getAllCourse();
	}

	public int saveCourse(int professorID, int courseID) throws SQLException {
		return profDAL.saveCourse(professorID, courseID);
	}
}
