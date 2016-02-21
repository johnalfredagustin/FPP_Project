package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.CourseDAL;
import orm.CourseObject;

public class CourseController {

	private CourseDAL course = new CourseDAL();

	public ArrayList<CourseObject> getAllCourse() throws SQLException {
		return course.getAllCourse();
	}
}
