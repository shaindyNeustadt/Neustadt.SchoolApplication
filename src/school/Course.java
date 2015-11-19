package school;

public class Course implements Comparable<Course> {

	private String courseID;
	private String courseName;
	private String departmentID;
	private int numCredits;

	// Standard Course constructor
	public Course(String courseID, String courseName, String departmentID,
			int numCredits) {
		if (courseID == null || courseID.equals("") || courseName == null
				|| courseName.equals("") || departmentID == null
				|| departmentID.equals("") || numCredits < 0 || numCredits > 4)
			throw new InvalidDataException();
		this.courseID = courseID;
		this.courseName = courseName;
		this.departmentID = departmentID;
		this.numCredits = numCredits;
	}

	// getters
	public String getCourseID() {
		return courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public int getnumCredits() {
		return numCredits;
	}

	@Override
	// compareTo
	public int compareTo(Course c) {
		return this.courseID.compareTo(c.getCourseID());
	}

	// equals
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof Course) {
			Course other = (Course) o;
			return (this.courseID == other.courseID);
		} else
			return false;
	}

	// toString
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nCourse ID: " + this.courseID);
		buffer.append(" Course Name: " + this.courseName);
		buffer.append(" Department ID: " + this.departmentID);
		buffer.append(" Number of Credits: " + this.numCredits);
		return buffer.toString();
	}
}
