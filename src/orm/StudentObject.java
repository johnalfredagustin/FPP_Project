package orm;

import java.util.GregorianCalendar;

public class StudentObject extends PersonObject {

	private int studentID;
	
	public StudentObject(String firstName, String lastName, GregorianCalendar DOB) {
		super(firstName, lastName, DOB);
	}
	
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
}
