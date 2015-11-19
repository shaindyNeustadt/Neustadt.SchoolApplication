package school;

public class TaughtCourse extends Course {
	private Integer teacherID;
	private Integer year;
	private Semester semesterID;
	private Section sectionID;

	// TaughtCourse constructor
	public TaughtCourse(Course course, Integer year, String semesterID,
			String sectionID, Integer teacherID) {
		super(course.getCourseID(), course.getCourseName(), course
				.getDepartmentID(), course.getnumCredits());
		if (teacherID == null || year == null || semesterID == null
				|| semesterID.equals("") || sectionID == null
				|| sectionID.equals(""))
			throw new InvalidDataException();
		this.teacherID = teacherID;
		this.year = year;
		this.semesterID = setSemester(semesterID);
		this.sectionID = setSection(sectionID);
	}

	// getters
	public Integer getTeacherID() {
		return teacherID;
	}

	public Integer getYear() {
		return year;
	}

	public String getSemesterID() {
		return semesterID.toString();
	}

	public String getSectionID() {
		return sectionID.toString();
	}

	// toString
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append(" Teacher ID: " + this.teacherID);
		buffer.append(" Year: " + this.year);
		buffer.append(" Semester: " + this.semesterID.toString());
		buffer.append(" Section: " + this.sectionID.toString());
		return buffer.toString();
	}

	// Semester validation
	private Semester setSemester(String semester) {
		for (Semester s : Semester.values()) {
			if (s.toString().equalsIgnoreCase(semester))
				return s;
		}
		throw new InvalidDataException();
	}

	// Section validation
	private Section setSection(String section) {
		for (Section s : Section.values()) {
			if (s.toString().equalsIgnoreCase(section))
				return s;
		}
		throw new InvalidDataException();
	}
}
