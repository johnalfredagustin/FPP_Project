package view;

import orm.CourseObject;

public abstract class HomeView {

	public abstract CourseObject[] getAllCourses();
	
	public abstract void registerCourse();
	
}
