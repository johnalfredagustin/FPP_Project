package orm;

import java.util.GregorianCalendar;

public class StudentObject extends PersonObject {
	
	private GregorianCalendar entryDate;
	
	public StudentObject(int ID) {
		super(ID);
	}
	
	public StudentObject(String firstName, String lastName, GregorianCalendar DOB) {
		super(firstName, lastName, DOB);
	}

	public GregorianCalendar getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(GregorianCalendar entryDate) {
		this.entryDate = entryDate;
	}

}
