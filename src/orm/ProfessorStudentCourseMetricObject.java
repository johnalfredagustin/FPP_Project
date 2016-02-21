package orm;

public class ProfessorStudentCourseMetricObject {

	private StudentObject student;
	private ProfessorObject professor;
	private CourseObject course;
	
	private double gradeNumber;
	private String gradeLetter;
	
	public ProfessorStudentCourseMetricObject(StudentObject student, ProfessorObject professor, CourseObject course) {
		this.student = student;
		this.professor = professor;
		this.course = course;
	}
		
	public CourseObject getCourse() {
		return course;
	}
	public void setCourse(CourseObject course) {
		this.course = course;
	}
	public PersonObject getStudent() {
		return student;
	}
	public void setStudent(StudentObject student) {
		this.student = student;
	}
	public PersonObject getProfessor() {
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
