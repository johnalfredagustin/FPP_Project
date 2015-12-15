package controller;

import java.sql.SQLException;
import model.StudentDAL;
import orm.StudentObject;

public class StudentController {

	private StudentDAL studentDAL = new StudentDAL();
	
	public StudentObject getStudentDetail(int studentID) throws SQLException {
		
		return studentDAL.getStudentDetail(studentID);
	}
	
	public boolean signUp(StudentObject studentObj) throws SQLException {
		return studentDAL.signup(studentObj);
	}
}
