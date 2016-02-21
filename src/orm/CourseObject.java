package orm;

public class CourseObject {

	private String courseCode;
	private String courseName;
	private String courseDesc;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String d) {
		this.courseName = d;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}	
}
