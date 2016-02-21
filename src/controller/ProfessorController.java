package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ProfessorDAL;
import model.ProfessorStudentCourseMetricDAL;
import orm.CourseObject;
import orm.PersonObject;
import orm.ProfessorStudentCourseMetricObject;

public class ProfessorController {

	private ProfessorDAL profDAL = new ProfessorDAL();
	private ProfessorStudentCourseMetricDAL profStudentCourse = new ProfessorStudentCourseMetricDAL();
	
	public PersonObject getProfessorDetail(int professorID) throws SQLException {
		return profDAL.getProfessorDetail(professorID);
	}
	
	public ArrayList<ProfessorStudentCourseMetricObject> getAllStudentCourseDetail() throws SQLException {
		return profStudentCourse.getAllStudentCourseDetail();
	}
	
	public ArrayList<CourseObject> getAllCourse() throws SQLException {
		return profDAL.getAllCourse();
	}
	
	public void saveCourse(int professorID, String courseCode) throws SQLException {
		profDAL.saveCourse(professorID, courseCode);
	}
	
}
