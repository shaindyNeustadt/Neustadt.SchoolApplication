package school;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Teacher extends Employee {
	private String departmentID;
	private String socialSecurityNumber;
	private Degree degree;
	private Major major;
	private Double salary;
	private ArrayList<TaughtCourse> taughtCourse;

	// constructor
	public Teacher(Integer ID, String firstName, String lastName,
			Character midInitial, String phoneNumber, Character gender,
			String street, String city, String state, String zipcode,
			GregorianCalendar dateHired, GregorianCalendar dateOfBirth,
			String employeeTypeID, String departmentID,
			String socialSecurityNumber, String degree, String major,
			Double salary) {
		super(ID, firstName, lastName, midInitial, phoneNumber, gender, street,
				city, state, zipcode, dateHired, dateOfBirth, employeeTypeID);
		if (departmentID == null || departmentID.equals("")
				|| socialSecurityNumber == null
				|| socialSecurityNumber.equals("") || degree == null
				|| degree.equals("") || major == null || major.equals("")
				|| salary < 15000 || salary > 100000)
			throw new InvalidDataException();
		this.departmentID = departmentID;
		this.socialSecurityNumber = socialSecurityNumber;
		this.degree = validateDegree(degree);
		this.major = validateMajor(major);
		this.salary = salary;
		this.taughtCourse = new ArrayList<TaughtCourse>();
	}

	// getters
	public String getDepartmentID() {
		return departmentID;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public Degree getDegree() {
		return degree;
	}

	public Major getMajor() {
		return major;
	}

	public Double getSalary() {
		return salary;
	}

	public ArrayList<TaughtCourse> getTaughtCourse() {
		if(taughtCourse == null || taughtCourse.size() == 0) {
			return null;
		}
		ArrayList<TaughtCourse> courseCopy = new ArrayList<TaughtCourse>();
		for(TaughtCourse t: taughtCourse){
			courseCopy.add(t);
		}
		return courseCopy;
	}

	// setters
	public void setDegree(String degree) {
		this.degree = validateDegree(degree);
	}

	public void setMajor(String major) {
		this.major = validateMajor(major);
	}

	public void setSalary(Integer raise) {
		if (raise <= 0)
			throw new InvalidDataException();
		this.salary += raise;
	}

	// to string
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nDepartment ID: " + departmentID);
		buffer.append(" Social Security Number: " + socialSecurityNumber);
		buffer.append(" Degree: " + degree.toString());
		buffer.append(" Major: " + major.toString());
		buffer.append(" Salary: $" + salary);
		if (taughtCourse != null && taughtCourse.size() != 0)
			buffer.append("\nTaught Courses: " + taughtCourse);
		return buffer.toString();
	}

	// apply raise
	public void applyRaise(Double percent) {
		this.salary += salary * percent;
	}

	// taught course
	public void taughtCourse(Course course, Integer year, String semester,
			String sectionID) {
		TaughtCourse newCourse = new TaughtCourse(course, year, semester,
				sectionID, super.getID());
		if(taughtCourse.contains(newCourse)){
				throw new InvalidDataException();
		}
		taughtCourse.add(newCourse);
	}

	// how many courses per semester a professor taught
	public int howManyCoursesPerSemester(Integer year, String semesterID) {
		int numCoursesTaught = 0;
		for (TaughtCourse c : taughtCourse) {
			if (c.getYear().equals(year)
					&& c.getSemesterID().equals(semesterID))
				numCoursesTaught++;
		}
		return numCoursesTaught;
	}

	// how many different courses a professor taught
	public int howManyDifferentCourses() {
		int numDifferentCourses = 0;
		Collections.sort(taughtCourse);

		for (int i = 0; i < taughtCourse.size() - 1; i++) {
			if (taughtCourse.get(i).compareTo(taughtCourse.get(i + 1)) < 0)
				numDifferentCourses++;
		}
		return numDifferentCourses + 1;
	}

	// Major validation
	private Major validateMajor(String major) {
		for (Major m : Major.values()) {
			if (major.equalsIgnoreCase(m.toString()))
				return m;
		}
		throw new InvalidDataException();
	}

	// Degree validation
	private Degree validateDegree(String degree) {
		for (Degree d : Degree.values()) {
			if (degree.equalsIgnoreCase(d.toString()))
				return d;
		}
		throw new InvalidDataException();
	}
}
