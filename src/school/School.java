package school;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class School {
String schoolName;
Address address;
String phoneNumber;
private ArrayList<Person> people;
private ArrayList<Course> courses;
private ArrayList<Department> departments;

//School constructor without files to be read
public School(String schoolName, Address address, String phoneNumber) throws FileNotFoundException{
	this(schoolName, address, phoneNumber, null, null, null, null);
}
//standard School constructor
public School(String schoolName, Address address, String phoneNumber, String teachFileName, String studentFileName,
		String departmentFileName, String courseFileName) throws FileNotFoundException{
	if(schoolName == null || schoolName.equals("") || address == null || !isValidNumber(phoneNumber))
		throw new InvalidDataException();
	this.schoolName = schoolName;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.people = new ArrayList<Person>();
	this.courses = new ArrayList<Course>();
	this.departments = new ArrayList<Department>();
	
	if(teachFileName != null)
		readTeachFile(teachFileName);
	if(studentFileName != null)
		readStudentFile(studentFileName);		
	if(departmentFileName != null)
		readDepartmentFile(departmentFileName);	
	if(courseFileName != null)
		readCourseFile(courseFileName);
	}
//validate the phone number
	private boolean isValidNumber(String phoneNumber){
		if(phoneNumber.matches("\\d{10}")){
			return true;
		}
		return false;
			}
//getters
public String getSchoolName() {
	return schoolName;
}
public Address getAddress() {
	return address;
}
public String getPhoneNumber() {
	return phoneNumber;
}
//return deep copy of Person ArrayList
public ArrayList<Person> getPeople() {
	ArrayList<Person> peopleCopy = new ArrayList<Person>();
	for(Person p: people){
		peopleCopy.add(p);
	}
	return peopleCopy;
}
//return deep copy of Course ArrayList
public ArrayList<Course> getCourses() {
	ArrayList<Course> courseCopy = new ArrayList<Course>();
	for(Course c: courses){
		courseCopy.add(c);
	}
	return courseCopy;
}
//return deep copy of Department ArrayList
public ArrayList<Department> getDepartments() {
	ArrayList<Department> departmentCopy = new ArrayList<Department>();
	for(Department d: departments){
		departmentCopy.add(d);
	}
	return departmentCopy;
}
//setters
public void setPhoneNumber(String phoneNumber){
	if(!isValidNumber(phoneNumber)){
		throw new InvalidDataException();
	}
	this.phoneNumber = phoneNumber;
}
//to string
public String toString(){
	StringBuffer buffer = new StringBuffer();
	buffer.append("\nSchool Name: "+ schoolName);
	buffer.append(" Address: "+ address.toString());
	buffer.append(" Phone Number: " + phoneNumber);
	buffer.append("\n\nTeachers: ");
	for(Person p : people){
		if(p instanceof Teacher)
			buffer.append(p);
	}
	buffer.append("\n\nStudents: ");
	for(Person p : people){
		if(p instanceof Student)
			buffer.append(p);
	}
	buffer.append( "\n\nCourses: " + courses);
	buffer.append("\n\nDepartments: " + departments);
	return buffer.toString();
	}
//add teacher
	public void addTeacher(Integer ID, String firstName, String lastName, Character midInitial, String phoneNumber, Character gender, 
			String street, String city, String state, String zipcode, GregorianCalendar dateHired, 
			GregorianCalendar dateOfBirth, String employeeTypeID, String departmentID, String socialSecurityNumber,
			String degree, String major, Double salary){
		
	Teacher t = new Teacher(ID, firstName, lastName, midInitial, phoneNumber, gender, street, city, state, zipcode,
			 dateHired, dateOfBirth, employeeTypeID, departmentID, socialSecurityNumber,
			degree, major, salary);
	//check to make sure this teacher was not already added
	if(people.contains(t)){
		throw new InvalidDataException();
	}
	people.add(t);
	}
	
//add student
	public void addStudent(Integer studentID, String firstName, String lastName, Character midInitial, String phoneNumber, Character gender, 
			String street, String city, String state, String zipcode, String major, GregorianCalendar dateOfBirth, String socialSecurityNumber){
		Student s = new Student(studentID, firstName, lastName, midInitial, phoneNumber, gender, street,
				city, state, zipcode, major, dateOfBirth, socialSecurityNumber);
		//check to make sure this student was not already added
		if(people.contains(s)){
				throw new InvalidDataException();
			}
		people.add(s);
	}
//add a Course
	public void addCourse(String courseID, String courseName, String departmentID, int numCredits){
		Course c = new Course(courseID, courseName, departmentID, numCredits);
		//check to make sure this course was not already added
			if(courses.contains(c)){
					throw new InvalidDataException();
				}
			courses.add(c);	
		}
//add a Department	
	public void addDepartment(String departmentID, String departmentName, String location, String phoneNumber, String faxNumber, String chairPerson){
	Department d = new Department(departmentID, departmentName, location, phoneNumber, faxNumber, chairPerson);
		//check if the Department already exists	
	if(departments.contains(d)){
			throw new InvalidDataException();
		}
	departments.add(d);
	}
	
//remove teacher
	public void removeTeacher(Integer teacherID){
		for(Person p : people){
			if(p.getID().equals(teacherID) && p instanceof Teacher){
			people.remove(p);
			return;
			}
		}
		throw new NotFoundException();
	}
//remove student
	public void removeStudent(Integer studentID){
		for(Person p: people){
			if(p.getID().equals(studentID) && p instanceof Student){ 
				people.remove(p);
				return;
			}
		}
		throw new NotFoundException();
	}
//remove course
	public void removeCourse(String courseID){
		for(Course c: courses){
			if(c.getCourseID().equals(courseID)){
				courses.remove(c);
				return;
			}
		}
		throw new NotFoundException();
	}
//modify teacher last name
public void modifyTeacherLastName(Integer ID, String newLastName){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Teacher){
			((Teacher)p).setLastName(newLastName);
			return;
		}
	}
	throw new NotFoundException();
	}
//modify Teacher Address
public void modifyTeacherAddress(Integer ID, Address newAddress){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Teacher){
			p.setAddress(newAddress);
			return;
		}
	}
	throw new NotFoundException();
	}
//Modify Teacher Degree
public void modifyTeacherDegree(Integer ID, String newDegree, String newMajor){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Teacher){
			((Teacher)p).setMajor(newMajor);
			((Teacher)p).setDegree(newDegree);
			return;
		}
	}
	throw new NotFoundException();
	}
//Give Teacher raise in percentage
public void giveTeacherRaise(Integer ID, Double percent){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Teacher){
			((Teacher)p).applyRaise(percent);
			return;
		}
	}
	throw new NotFoundException();
	}
//Give Teacher a raise in amount
public void giveTeacherRaise(Integer ID, Integer amount){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Teacher){
			((Teacher)p).setSalary(amount);
			return;
		}
	}
	throw new NotFoundException();
	}
//Modify a Student last name
public void modifyStudentLastName(Integer ID, String newLastName){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Student){
			((Student)p).setLastName(newLastName);
			return;
		}
	}
	throw new NotFoundException();
}
//Modify a Student phone number
public void modifyStudentPhoneNumber(Integer ID, String newPhoneNumber){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Student){
			((Student)p).setPhoneNumber(newPhoneNumber);
			return;
		}
	}
	throw new NotFoundException();
}
//Add a new Completed Course for a specific Student
public void addCompletedCourse(Integer ID, String courseID, String grade){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Student){
			for(Course c : courses){
				if(c.getCourseID().equals(courseID)){
					((Student)p).completeCourse(c, grade);
					return;
				}
			}
		}	
	}
	throw new NotFoundException();
}
//Get Student GPA
public Double getStudentGPA(Integer ID){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Student)
			return ((Student)p).getGPA();
			}
	throw new NotFoundException();
}
//Get grade of a Student Completed Course
public Grade getGradeOfCourse(Integer ID, String courseID){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Student)
		return ((Student)p).getGradeOfCourse(courseID);
		}
		throw new NotFoundException();
}
//Get Completed Courses of a Student in a specific Department
public ArrayList<CompletedCourse> getCoursesByDepartment(Integer ID, String departmentID){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Student)
			return ((Student)p).getCoursesByDepartment(departmentID);
			}
	throw new NotFoundException();
}
//Get Completed Courses of a Student that have a specific Grade
public ArrayList<CompletedCourse> getCoursesByGrade(Integer ID, String grade){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Student)
			return ((Student)p).getCoursesByGrade(grade);
			}
	throw new NotFoundException();
}
//Get Teacher list sorted alphabetically 
public ArrayList<Teacher> getTeachersSortedByName(){
	ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	for(Person p: people){
		if(p instanceof Teacher){
			teachers.add((Teacher)p);
		}
			}
	Collections.sort(teachers, new Comparator<Person>(){
        public int compare(Person t1, Person t2){
        	if (t1.getLastName().equals(t2.getLastName()))
    			return (t1.getFirstName().compareTo(t2.getFirstName()));
    		else
    			return (t1.getLastName().compareTo(t2.getLastName()));
        }
});
	return teachers; 
	}
//Get Teacher list sorted by ID
public ArrayList<Teacher> getTeachers(){
ArrayList<Teacher> teachers = new ArrayList<Teacher>();
for(Person p: people){
	if(p instanceof Teacher)
		teachers.add((Teacher)p);
				}
Collections.sort(teachers);
return teachers;
}
//Get Student list sorted alphabetically
public ArrayList<Student> getStudentsSortedByName(){
	ArrayList<Student> students = new ArrayList<Student>();
	for(Person p: people){
		if(p instanceof Student){
			students.add((Student)p);
		}
			}
	Collections.sort(students, new Comparator<Person>(){
        public int compare(Person t1, Person t2){
        	if (t1.getLastName().equals(t2.getLastName()))
    			return (t1.getFirstName().compareTo(t2.getFirstName()));
    		else
    			return (t1.getLastName().compareTo(t2.getLastName()));
        }
});
	return students; 
}
//Get Students sorted by ID
public ArrayList<Student> getStudents(){
	ArrayList<Student> students = new ArrayList<Student>();
	for(Person p: people){
		if(p instanceof Student)
			students.add((Student)p);
					}
	Collections.sort(students);
	return students;
	}
//Add a taught Course for a specific Teacher
public void addTaughtCourse(Integer ID, String courseID, Integer year, String semester, String section){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Teacher){
		for(Course c: courses){
			if(c.getCourseID().equals(courseID)){
				((Teacher)p).taughtCourse(c, year, semester, section);
				return;
			}
		}
		}
	}
	throw new NotFoundException();
}
//Get how many courses a Teacher taught in a specific semester
public int howManyCoursesPerSemester(Integer ID, Integer year, String semester){
	for(Person p: people){
		if(p.getID().equals(ID) && p instanceof Teacher)
		return ((Teacher)p).howManyCoursesPerSemester(year, semester);
	}
	throw new NotFoundException();
}
//read Teacher File
	private void readTeachFile(String teachFileName) throws FileNotFoundException{
		Scanner input = new Scanner(new File(teachFileName));
		while(input.hasNext()){
			Integer ID = input.nextInt();
			String firstName = input.next();
			String lastName = input.next();
			input.nextLine();
			String street = input.nextLine();
			String[] cityState = input.nextLine().split(",");
			String city = cityState[0];
			String state = cityState[1];
			String zipcode = input.next();
			String telNumber = input.next();
			Character gender = input.next().charAt(0);
			String hireDate = input.next();
			String birthDate = input.next();
			String employeeType = input.next();
			String departmentCode = input.next();
			String socialSecurityNumber = input.next();
			String degree = input.next();
			String major = input.next();
			Double salary = input.nextDouble();
			
			//change date hired from string to Gregorian calendar value
			String date[] = hireDate.split("/");
			GregorianCalendar dateHired = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0])-1,
					Integer.parseInt(date[1])-1);
			
			//change date of birth from string to Gregorian calendar value
			String date2[] = birthDate.split("/");
			GregorianCalendar dateOfBirth = new GregorianCalendar(Integer.parseInt(date2[2]), Integer.parseInt(date2[0])-1,
					Integer.parseInt(date2[1]));
			
			//add a new instance of the Teacher class to the people ArrayList
			people.add(new Teacher(ID, firstName, lastName, null, telNumber, gender, street, city, state, zipcode,
						 dateHired, dateOfBirth, employeeType, departmentCode, socialSecurityNumber,
						degree, major, salary));
		}
	}
//read Student file	
	private void readStudentFile(String studentFileName)throws FileNotFoundException{
		Scanner input = new Scanner(new File(studentFileName));
		while(input.hasNext()){
		Integer studentID = input.nextInt();
		String lastName = input.next();
		String firstName = input.next();
		Character midInitial = input.next().charAt(0);
		input.nextLine();
		String[] cityState = input.nextLine().split(",");
		String city = cityState[0];
		String state = cityState[1];
		String telNumber = input.next();
		Character gender = input.next().charAt(0);
		String major = input.next();
		
		people.add(new Student(studentID, firstName, lastName, midInitial, telNumber, gender, null,
				city, state, null, major, null, null));
		}
		}
//read Department file	
	private void readDepartmentFile(String departmentFileName)throws FileNotFoundException{
		Scanner input = new Scanner(new File(departmentFileName));
		while(input.hasNext()){
			String[] departmentValues = input.nextLine().split(";");
			String departmentID = departmentValues[0];
			String departmentName = departmentValues[1];
			String location = departmentValues[2];
			String telNumber = departmentValues[3];
			String faxNumber = departmentValues[4];
			String chairID = departmentValues[5];
			
			departments.add(new Department(departmentID, departmentName, location, telNumber, faxNumber, chairID));
		}
}
//read Course file
	private void readCourseFile(String courseFileName)throws FileNotFoundException{
		Scanner input = new Scanner(new File(courseFileName));
		while(input.hasNext()){
			String[] courseValues = input.nextLine().split(";");
			String courseID = courseValues[0];
			String courseName = courseValues[1];
			String departmentID = courseValues[2];
			//String numCredits = courseValues[3].trim();
			int numCredits = Integer.parseInt(courseValues[3].trim());
			
			courses.add(new Course(courseID, courseName, departmentID, numCredits));
		}
	}

}
