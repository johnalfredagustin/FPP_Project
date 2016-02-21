package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ProfessorStudentCourseMetricDAL;
import orm.ProfessorStudentCourseMetricObject;

public class ProfessorGradeController {

	ProfessorStudentCourseMetricDAL profStudentCourse = new ProfessorStudentCourseMetricDAL();
	
	public ArrayList<ProfessorStudentCourseMetricObject> getAllStudentCourseDetail() throws SQLException {
		return profStudentCourse.getAllStudentCourseDetail();
	}
	
}
