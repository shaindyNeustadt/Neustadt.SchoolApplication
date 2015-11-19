package school;

import java.util.GregorianCalendar;

public class CompletedCourse extends Course {

	private Integer studentID;
	private Grade grade;
	private GregorianCalendar completedDate;

	// CompletedCourse constructor
	public CompletedCourse(Course course, Integer studentID, String grade) {
		super(course.getCourseID(), course.getCourseName(), course
				.getDepartmentID(), course.getnumCredits());
		if (studentID == null || grade == null || grade.equals(""))
			throw new InvalidDataException();
		this.studentID = studentID;
		this.grade = setGrade(grade);
		this.completedDate = new GregorianCalendar();
	}

	// validate Grade value
	private Grade setGrade(String grade) {
		for (Grade g : Grade.values()) {
			if (g.toString().equalsIgnoreCase(grade))
				return g;
		}
		throw new InvalidDataException();
	}

	// get Grade
	public Grade getGrade() {
		return this.grade;
	}
}
