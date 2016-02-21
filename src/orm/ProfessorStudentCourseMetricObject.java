package orm;

public class ProfessorStudentCourseMetricObject {

	private StudentObject student;
	private ProfessorObject professor;
	private CourseObject course;
	
	private double gradeNumber;
	private String gradeLetter;
	
	public CourseObject getCourse() {
		return course;
	}
	public void setCourse(CourseObject course) {
		this.course = course;
	}
	public StudentObject getStudent() {
		return student;
	}
	public void setStudent(StudentObject student) {
		this.student = student;
	}
	public ProfessorObject getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorObject professor) {
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
