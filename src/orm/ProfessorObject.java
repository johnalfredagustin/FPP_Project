package orm;

import java.util.GregorianCalendar;

public class ProfessorObject extends PersonObject {

	private String SSN;
	
	public ProfessorObject(String firstName, String lastName, GregorianCalendar DOB) {
		super(firstName, lastName, DOB);
	}
	
	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}
}
