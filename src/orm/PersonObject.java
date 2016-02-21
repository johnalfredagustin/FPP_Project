package orm;

import java.util.GregorianCalendar;

public class PersonObject {
	
	private String firstName;
	private String lastName;
	private GregorianCalendar DOB;
	private String email;
	
	public PersonObject(String firstName, String lastName, GregorianCalendar DOB) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.DOB = DOB;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public GregorianCalendar getDOB() {
		return DOB;
	}


	public void setDOB(GregorianCalendar dOB) {
		DOB = dOB;
	}

	
	
}
