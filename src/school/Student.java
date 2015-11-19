package school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Student extends Person {
	private Major major;
	private GregorianCalendar enrolledDate;
	private GregorianCalendar dateOfBirth;
	private Double GPA;
	private int creditsEarned;
	private String socialSecurityNumber;
	private ArrayList<CompletedCourse> completedCourse;
	
	//constructor with no major
		public Student(Integer ID, String firstName, String lastName,
				Character midInitial, String phoneNumber, Character gender,
				String street, String city, String state, String zipcode,
				GregorianCalendar dateOfBirth, String socialSecurityNumber) {
			this(ID, firstName, lastName, midInitial, phoneNumber, gender, street,
					city, state, zipcode, null, dateOfBirth, socialSecurityNumber);
				}
	//constructor with no socialSecurityNumber and no dateOfBirth
	public Student(Integer ID, String firstName, String lastName,
			Character midInitial, String phoneNumber, Character gender,
			String street, String city, String state, String zipcode,
			String major) {
		this(ID, firstName, lastName, midInitial, phoneNumber, gender, street,
				city, state, zipcode, major, null, null);
			}
	//standard Student constructor
	public Student(Integer ID, String firstName, String lastName,
			Character midInitial, String phoneNumber, Character gender,
			String street, String city, String state, String zipcode,
			String major, GregorianCalendar dateOfBirth,
			String socialSecurityNumber) {
		super(ID, firstName, lastName, midInitial, phoneNumber, gender, street,
				city, state, zipcode);
		if (major == null || major.equals(""))
			this.major = Major.UDCD;
		else {
			Major m = validateMajor(major);
			this.major = m;
		}
		this.GPA = null;
		this.creditsEarned = 0;
		this.dateOfBirth = dateOfBirth;
		this.socialSecurityNumber = socialSecurityNumber;
		this.completedCourse = new ArrayList<CompletedCourse>();
		this.enrolledDate = new GregorianCalendar();
	}

	// getters
	public Major getMajor() {
		return major;
	}

	public GregorianCalendar getEnrolledDate() {
		return new GregorianCalendar(enrolledDate.get(Calendar.YEAR),
				enrolledDate.get(Calendar.MONTH),
				enrolledDate.get(Calendar.DAY_OF_MONTH));
	}

	public GregorianCalendar getDateOfBirth() {
		return new GregorianCalendar(dateOfBirth.get(Calendar.YEAR),
				dateOfBirth.get(Calendar.MONTH),
				dateOfBirth.get(Calendar.DAY_OF_MONTH));
	}

	public Double getGPA() {
		return GPA;
	}

	public int getCreditsEarned() {
		return creditsEarned;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public ArrayList<CompletedCourse> getCompletedCourse() {
		if(completedCourse == null || completedCourse.size() == 0){
		return null;	
		}
		ArrayList<CompletedCourse> courseCopy = new ArrayList<CompletedCourse>();
		for(CompletedCourse c: completedCourse){
			courseCopy.add(c);
		}
		return courseCopy;
	}

	// setters
	public void setMajor(String major) {
		if (major == null || major.equals(""))
			this.major = Major.UDCD;
		else {
			Major m = validateMajor(major);
			this.major = m;
		}
	}
	// calculate new GPA based on completed course	
	private Double setGPA(CompletedCourse newCC) {
		double pointValue =0;
		for(CompletedCourse c : completedCourse){
			pointValue += (c.getGrade().getGradeValue() * c.getnumCredits());
		}
		double newGPA = (pointValue + newCC.getGrade().getGradeValue()*newCC.getnumCredits())
				/ (creditsEarned + newCC.getnumCredits());
		
		if (newGPA < 0 || newGPA > 4)
			throw new InvalidDataException();
		return newGPA;
	}
	//set credits earned
	public void setCreditsEarned(int creditsEarned) {
		if (creditsEarned < 0)
			throw new InvalidDataException();
		this.creditsEarned = creditsEarned;
	}

	// to String
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YY");

		buffer.append(super.toString());
		buffer.append("\nMajor: " + major.toString());
		buffer.append(" Enrolled Date: "
				+ formatter.format(enrolledDate.getTime()));
		if (dateOfBirth != null)
			buffer.append(" Date of Birth: "
					+ formatter.format(dateOfBirth.getTime()));
		if (GPA != null)
			buffer.append(" GPA: " + this.GPA);
		buffer.append(" Credits Earned: " + this.creditsEarned);
		if (socialSecurityNumber != null)
			buffer.append(" Social Security Number: "
					+ this.socialSecurityNumber);
		if (completedCourse.size() > 0)
			buffer.append("\nCompleted Courses: " + this.completedCourse);
		else
			buffer.append(" Completed Courses: N/A");
		return buffer.toString();
	}
//add completed course
	public void completeCourse(Course course, String grade) {
		CompletedCourse newCompletedCourse = new CompletedCourse(course,
				super.getID(), grade);
		// set and validate GPA value
		this.GPA = setGPA(newCompletedCourse);
		// set credits earned
		this.creditsEarned += course.getnumCredits();
		// add completed course to completedCourse ArrayList
		completedCourse.add(newCompletedCourse);
	}
//find completed course
	public CompletedCourse findCompletedCourse(String courseID) {
		for (CompletedCourse c : completedCourse) {
			if (courseID.equals(c.getCourseID()))
				return new CompletedCourse(c, super.getID(), c.getGrade().toString());
		}
		throw new NotFoundException();
	}
//get grade of Completed Course
	public Grade getGradeOfCourse(String courseID) {
		for (CompletedCourse c : completedCourse) {
			if (courseID.equals(c.getCourseID()))
				return c.getGrade();
		}
		throw new NotFoundException();
	}
//get Courses by Department
	public ArrayList<CompletedCourse> getCoursesByDepartment(String departmentID) {
		ArrayList<CompletedCourse> coursesByDepartment = new ArrayList<CompletedCourse>();
		for (CompletedCourse c : completedCourse) {
			if (departmentID.equals(c.getDepartmentID()))
				coursesByDepartment.add(c);
		}
		return coursesByDepartment;
	}
//get Courses by grade
	public ArrayList<CompletedCourse> getCoursesByGrade(String grade) {
		ArrayList<CompletedCourse> coursesByGrade = new ArrayList<CompletedCourse>();
		for (CompletedCourse c : completedCourse) {
			if (grade.equals(c.getGrade().toString()))
				coursesByGrade.add(c);
		}
		return coursesByGrade;
	}

	// validate major is valid
	private Major validateMajor(String major) {
		for (Major m : Major.values()) {
			if (major.equalsIgnoreCase(m.name()))
				return m;
		}
		throw new InvalidDataException();
	}
}
