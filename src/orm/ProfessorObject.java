package orm;

import java.util.GregorianCalendar;

public class ProfessorObject extends PersonObject {

	private int professorID;
	private String SSN;
	
	public ProfessorObject(String firstName, String lastName, GregorianCalendar DOB) {
		super(firstName, lastName, DOB);
	}
	
	public int getProfessorID() {
		return professorID;
	}
	
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}
	
//	public String getFirstName() {
//		return FirstName;
//	}
//	public void setFirstName(String firstName) {
//		FirstName = firstName;
//	}
//	public String getLastName() {
//		return LastName;
//	}
//	public void setLastName(String lastName) {
//		LastName = lastName;
//	}
	
	
}
