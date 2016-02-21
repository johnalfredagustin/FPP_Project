package orm;

public class ProfessorStudentCourseMetricObject {

	private PersonObject student;
	private PersonObject professor;
	private CourseObject course;
	
	private double gradeNumber;
	private String gradeLetter;
	
	public CourseObject getCourse() {
		return course;
	}
	public void setCourse(CourseObject course) {
		this.course = course;
	}
	public PersonObject getStudent() {
		return student;
	}
	public void setStudent(PersonObject student) {
		this.student = student;
	}
	public PersonObject getProfessor() {
		return professor;
	}
	public void setProfessor(PersonObject professor) {
		this.professor = professor;
	}
	public double getGradeNumber() {
		return gradeNumber;
	}
	public void setGradeNumber(double gradeNumber) {
		this.gradeNumber = gradeNumber;
	}
	public String getGradeLetter() {
		return gradeLetter;
	}
	public void setGradeLetter(String gradeLetter) {
		this.gradeLetter = gradeLetter;
	}
	
	
}
