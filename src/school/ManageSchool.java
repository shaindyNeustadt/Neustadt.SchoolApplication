package school;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
public class ManageSchool {

	public static void main(String[] args) {
		School mySchool = null;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("School Name:");
		String schoolName = keyboard.nextLine();
		System.out.println("School Address: \nStreet:");
		String street = keyboard.nextLine();
		System.out.println("City:");
		String city = keyboard.nextLine();
		System.out.println("State:");
		String state = keyboard.nextLine();
		System.out.println("Zipcode:");
		String zipcode = keyboard.nextLine();
		Address address = new Address(street, city, state, zipcode);
		System.out.println("Phone Number:");
		String phoneNumber = keyboard.nextLine();
		System.out.println("Name of Teacher file:");
		String tFile = keyboard.nextLine();
		System.out.println("Name of Student file:");
		String sFile = keyboard.nextLine();
		System.out.println("Name of Department file:");
		String dFile = keyboard.nextLine();
		System.out.println("Name of Course file:");
		String cFile = keyboard.nextLine();
	try{	
	mySchool = new School(schoolName, address, phoneNumber, tFile, sFile, dFile, cFile);
	}catch(FileNotFoundException ex){
		System.out.println("File Not Found!");
		}
	
	int choice;
	do{
	choice = menu(keyboard);
	boolean found = false;
	
	switch(choice){
	case -1:
		System.out.println("Good bye. Have a great day!");
		System.exit(1);
	case 0:
		break;
	case 1:
		System.out.println("Add a new Teacher:");
		Person p = getPersonData(keyboard);
		System.out.println("Date Hired: (MM/dd/YY)");
		String dateHired = keyboard.nextLine();
		System.out.println("Date of Birth: (MM/dd/YY)");
		String dob = keyboard.nextLine();
		System.out.println("Employee type:");
		String employeeType = keyboard.nextLine();
		System.out.println("Department ID:");
		String departmentID = keyboard.nextLine();
		System.out.println("Social Security Number:");
		String ssn = keyboard.nextLine();
		System.out.println("Degree:");
		String degree = keyboard.nextLine();
		System.out.println("Major:");
		String major = keyboard.nextLine();
		System.out.println("Salary:");
		Double salary = keyboard.nextDouble();
		
		//change date hired from string to Gregorian calendar value
		String date[] = dateHired.split("/");
		GregorianCalendar hireDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[0])-1,
				Integer.parseInt(date[1])-1);
		
		//change date of birth from string to Gregorian calendar value
		String date2[] = dob.split("/");
		GregorianCalendar dateOfBirth = new GregorianCalendar(Integer.parseInt(date2[2]), Integer.parseInt(date2[0])-1,
				Integer.parseInt(date2[1]));
		
		try{
			mySchool.addTeacher(p.getID(), p.getFirstName(), p.getLastName(), p.getMidInitial(), p.getPhoneNumber(), p.getGender(), 
					p.getAddress().getStreet(), p.getAddress().getCity(), p.getAddress().getState(), p.getAddress().getZipcode(),
					hireDate, dateOfBirth, employeeType, departmentID, ssn, degree, major, salary);
			found = true;
		}catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Request can not be completed.");
		}
		if(found)
		System.out.println("A new Teacher was successfully added.");
		break;
	case 2:
		System.out.println("Remove a teacher:");
		System.out.println("Enter the Teacher ID:");
		try{
		mySchool.removeTeacher(keyboard.nextInt());
		keyboard.nextLine();
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable.");
		}
		if(found)
		System.out.println("The Teacher was sucessfully removed.");
		break;
	case 3:
		System.out.println("Modify a Teacher last name:");
		System.out.println("Enter the Teacher ID:");
		Integer ID = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the new last name");
		String newLastName = keyboard.nextLine();
		try{
		mySchool.modifyTeacherLastName(ID, newLastName);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("The Teacher last name was successfully modified.");
		break;
	case 4:
		System.out.println("Modify a Teacher Address:");
		System.out.println("Enter the Teacher ID:");
		Integer ID1 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the street:");
		String street1 = keyboard.nextLine();
		System.out.println("Enter the city:");
		String city1 = keyboard.nextLine();
		System.out.println("Enter the state:");
		String state1 = keyboard.nextLine();
		System.out.println("Enter the Zipcode:");
		String zipcode1 = keyboard.nextLine();
		try{
		mySchool.modifyTeacherAddress(ID1, new Address(street1, city1, state1, zipcode1));
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("The Teacher Address was successfully modified.");
		break;
	case 5:
		System.out.println("Modify a Teacher Degree:");
		System.out.println("Enter the Teacher ID:");
		Integer ID2 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the new Degree:");
		String newDegree = keyboard.nextLine();
		System.out.println("Enter the new Major:");
		String newMajor = keyboard.nextLine();
		try{
		mySchool.modifyTeacherDegree(ID2, newDegree, newMajor);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("The Teacher Degree was successfully modified.");
		break;
	case 6:
		System.out.println("Give Teacher a raise:");
		System.out.println("Enter the Teacher ID:");
		Integer ID3 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the percentage raise:");
		Double raise = keyboard.nextDouble();
		try{
		mySchool.giveTeacherRaise(ID3, raise);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("The Teacher salary was successfully modified.");
		break;
	case 7:
		System.out.println("Give Teacher a bonus:");
		System.out.println("Enter the Teacher ID:");
		Integer ID4 = keyboard.nextInt();
		System.out.println("Enter the bonus amount:");
		Integer bonus = keyboard.nextInt();
		keyboard.nextLine();
		try{
		mySchool.giveTeacherRaise(ID4, bonus);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("The Teacher salary was successfully modified.");
		break;	
	case 8:
		System.out.println("Add a taught course:");
		System.out.println("Enter the Teacher ID:");
		Integer ID5 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the Course ID:");
		String courseID = keyboard.nextLine();
		System.out.println("Enter the Year:");
		Integer year = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the Semester:");
		String semester = keyboard.nextLine();
		System.out.println("Enter the Section:");
		String section = keyboard.nextLine();
		try{
		mySchool.addTaughtCourse(ID5, courseID, year, semester, section);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("A new taught course was successfully added.");
		break;
	case 9:
		System.out.println("View Teachers in alphabetic order:");
		System.out.println(mySchool.getTeachersSortedByName());
		break;
	case 10:
		System.out.println("View Teachers sorted by ID:");
		System.out.println(mySchool.getTeachers());
		break;
	case 11:
		System.out.println("View how many courses a Teacher taught in a specified semester");
		System.out.println("Enter Teacher ID:");
		Integer id6 = keyboard.nextInt();
		System.out.println("Enter year:");
		Integer year1 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter Semester:");
		String semester1 = keyboard.nextLine();
		try{
		System.out.println("Number of courses taught: " +
		mySchool.howManyCoursesPerSemester(id6, year1, semester1));	
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}	
		break;
	case 12:
		System.out.println("Add a new Student:");
		Person s = getPersonData(keyboard);
		System.out.println("Major:");
		String majo = keyboard.nextLine();
		System.out.println("Date of Birth: (MM/dd/YY)");
		String dateOB = keyboard.nextLine();
		System.out.println("Social Security Number:");
		String sSN = keyboard.nextLine();
		
		//change date of birth from string to Gregorian calendar value
				String date3[] = dateOB.split("/");
				GregorianCalendar dateOfB = new GregorianCalendar(Integer.parseInt(date3[2]), Integer.parseInt(date3[0])-1,
						Integer.parseInt(date3[1]));
		try{
			mySchool.addStudent(s.getID(), s.getFirstName(), s.getLastName(), s.getMidInitial(), s.getPhoneNumber(), s.getGender(), 
					s.getAddress().getStreet(), s.getAddress().getCity(), s.getAddress().getState(), s.getAddress().getZipcode(),
					majo, dateOfB, sSN);
			found = true;
		}catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Request can not be completed.");
		}
		if(found){
			System.out.println("A new Student was successfully added.");
		}
		break;
	case 13:
		System.out.println("Remove a Student:");
		System.out.println("Enter the Student ID:");
		try{
		mySchool.removeStudent(keyboard.nextInt());
		keyboard.nextLine();
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessible. Task can not be completed.");
			}
		if (found)
		System.out.println("The student was successfully removed.");
		break;
	case 14:
		System.out.println("Modify a Student last name:");
		System.out.println("Enter the Student ID:");
		Integer ID6 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the new last name:");
		String newLastName1 = keyboard.nextLine();
		try{
		mySchool.modifyStudentLastName(ID6, newLastName1);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("Student last name was successfully modified.");
		break;
	case 15:
		System.out.println("Modify a Student phone number:");
		System.out.println("Enter the Student ID:");
		Integer ID7 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the new phone number:");
		String newPhoneNumber = keyboard.nextLine();
		try{
		mySchool.modifyStudentPhoneNumber(ID7, newPhoneNumber);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("Student phone number was successfully modified.");
		break;
	case 16:
		System.out.println("Add a completed course to a Student's record");
		System.out.println("Enter the Student ID:");
		Integer ID8 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the Course ID:");
		String courseID1 = keyboard.nextLine();
		System.out.println("Enter the grade:");
		String grade = keyboard.nextLine();
		try{
		mySchool.addCompletedCourse(ID8, courseID1, grade);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("A new Completed Course was successfully added to a Student record.");
		break;
	case 17:
		Double gpa = null;
		System.out.println("View student GPA:");
		System.out.println("Enter the Student ID:");
		Integer ID9 = keyboard.nextInt();
		keyboard.nextLine();
		try{
			gpa = mySchool.getStudentGPA(ID9);
			found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found && gpa == null)
			System.out.println("The requested student has no completed courses on record.");
		else if(found)
			System.out.println("GPA: " + gpa);
		break;
	case 18:
		System.out.println("View Student grade of a specified course:");
		System.out.println("Enter the Student ID:");
		Integer ID10 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the Course ID:");
		String courseID2 = keyboard.next();
		try{
		System.out.println("Course: " + courseID2 +
				" Grade: " + mySchool.getGradeOfCourse(ID10, courseID2));
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		break;	
	case 19:
		System.out.println("View completed courses of a specified department:");
		System.out.println("Enter the Student ID:");
		Integer ID11 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the Department ID:");
		String departmentID1 = keyboard.next();
		try{
		System.out.println("DepartmentID: " + departmentID1 +
				mySchool.getCoursesByDepartment(ID11, departmentID1));
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		break;		
	case 20:
		System.out.println("View completed courses which the student earened a specified grade:");
		System.out.println("Enter the Student ID:");
		Integer ID12 = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("Enter the grade:");
		String grade1 = keyboard.next();
		try{
		ArrayList<CompletedCourse> cc = mySchool.getCoursesByGrade(ID12, grade1);
		if(cc == null || cc.size() == 0)
			System.out.println("There are no courses with the grade: " + grade1);
		else
		System.out.println("Grade: " + grade1 +
		mySchool.getCoursesByGrade(ID12, grade1));
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		break;	
	case 21:
		System.out.println("View Students in alphabetic order:");
		System.out.println(mySchool.getStudentsSortedByName());
		break;
	case 22:
		System.out.println("View Students sorted by ID:");
		System.out.println(mySchool.getStudents());
		break;	
	case 23:
		System.out.println("Add a Course:");
		System.out.println("Enter the Course ID:");
		String courseID3 = keyboard.nextLine();
		System.out.println("Enter the Course Name:");
		String courseName = keyboard.nextLine();
		System.out.println("Enter the Department ID:");
		String departmentID2 = keyboard.next();
		System.out.println("Enter the number of credits:");
		int numOfCredits = keyboard.nextInt();
		keyboard.nextLine();
		try{
		mySchool.addCourse(courseID3, courseName, departmentID2, numOfCredits);
		found = true;
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("A new Course was successfully added.");
		break;
	case 24:
		System.out.println("Remove a Course:");
		System.out.println("Enter the Course ID:");
		String course = keyboard.nextLine();
		try{
		mySchool.removeCourse(course);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		if(found)
		System.out.println("Course: " + course + " was successfully removed.");
		break;
	case 25:
		System.out.println("Add a Department:");
		System.out.println("Enter the Department ID:");
		String departmentID3 = keyboard.nextLine();
		System.out.println("Enter the Department Name:");
		String departmentName = keyboard.nextLine();
		System.out.println("Enter the Location:");
		String location = keyboard.nextLine();
		System.out.println("Enter the phone number:");
		String phoneNumber1 = keyboard.nextLine();
		System.out.println("Enter the fax number:");
		String faxNumber = keyboard.nextLine();
		System.out.println("Enter the chair person ID:");
		String chairPerson = keyboard.nextLine();
		try{
		mySchool.addDepartment(departmentID3, departmentName, location, phoneNumber1, faxNumber, chairPerson);
		found = true;
		}catch(NotFoundException ex){
			System.out.println("The requested files are not accessable. Task can not be completed.");
		}
		catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Task can not be completed.");
		}
		if(found)
		System.out.println("A new Department was successfully added.");
		break;
	case 26:
		System.out.println("Modify the School phone number:");
		System.out.println("Enter the phone number:");
		String phoneNumber2 = keyboard.nextLine();
		try{
		mySchool.setPhoneNumber(phoneNumber2);
		found = true;
		}catch(InvalidDataException ex){
			System.out.println("Invalid Data entered. Request can not be completed.");
		}
		if(found)
			System.out.println("The School phone number was successfully modidfied.");
		break;
	case 27:
		System.out.println("My School:");
		System.out.println(mySchool.toString());
		break;
	}
	}while(choice != -1);
	
	}
	public static int menu(Scanner keyboard){
		int choice =0;
		do{
		System.out.println("\nAdd modify or view a record of:" +
						   "\n1. A Teacher" +
						   "\n2. A Student" +
						   "\n3. A Course" +
						   "\n4. A Department" +
						   "\n5. The School" +
						   "\n\nEnter -1 to exit");
		choice = keyboard.nextInt();
		}while(choice <-1 || choice >5);
		switch(choice){
		case -1:
			return -1;
		case 1:
			System.out.println("\n1. Add a new Teacher"+
								"\n2. Remove a Teacher"+
								"\n3. Modify a Teacher last name"+
								"\n4. Modify a Teacher address"+
								"\n5. Modify a Teacher degree"+
								"\n6. Give Teacher a raise (percentage increase)"+
								"\n7. Give Teacher a bonus"+
								"\n8. Add a taught course"+
								"\n9. View Teachers in alphabetic order"+
								"\n10. View Teachers sorted by ID"+
								"\n11. View how many courses a Teacher taught in a specified semester" +
								"\n\nEnter 0 to return to the previous menu.");
			int option6 = keyboard.nextInt();
			keyboard.nextLine();
			return option6;
		case 2:
			System.out.println("\n1. Add a new Student" +
								"\n2. Remove a Student" +
								"\n3. Modify a Student last name" +
								"\n4. Modify a Student phone number" +
								"\n5. Add a completed course to a Student's record" +
								"\n6. View Student GPA" +
								"\n7. View Student grade of a specified course" +
								"\n8. View completed courses of a specified department" +
								"\n9. View completed courses which the student earened a specified grade" +
								"\n10. View Students in alphabetic order" +
								"\n11. View Students sorted by ID"+
								"\n\nEnter 0 to return to the previous menu.");
			int option = keyboard.nextInt();
			keyboard.nextLine();
			if(option == 0)
				return 0;
			return (option + 11);
			
		case 3:
			System.out.println("\n1. Add a Course" +
								"\n2. Remove a Course"+
								"\n\nEnter 0 to return to the previous menu.");
			int option1 = keyboard.nextInt();
			keyboard.nextLine();
			if(option1 == 0)
				return 0;
			return (option1 + 22);
		case 4:
			System.out.println("\n1. Add a Department"+
								"\n\nEnter 0 to return to the previous menu.");
			int option2 = keyboard.nextInt();
			keyboard.nextLine();
			if(option2 == 0)
				return 0;
			return (option2 + 24);
		case 5:
			System.out.println("\n1. Modify the School phone number" +
								"\n2. View all School records"+
								"\n\nEnter 0 to return to the previous menu.");
			int option3 = keyboard.nextInt();
			keyboard.nextLine();
			if(option3 == 0)
				return 0;
			return (option3 + 25);
		default:
			return 0;
		}
		
		}
	private static Person getPersonData(Scanner keyboard){
		System.out.println("ID:");
		Integer ID = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("First name:");
		String first = keyboard.nextLine();
		System.out.println("Last name:");
		String last = keyboard.nextLine();
		System.out.println("Middle Initial:");
		Character mid = keyboard.nextLine().charAt(0);
		System.out.println("Phone Number:");
		String phone = keyboard.nextLine();
		System.out.println("Gender:");
		Character gender = keyboard.nextLine().charAt(0);
		System.out.println("Street:");
		String street = keyboard.nextLine();
		System.out.println("City:");
		String city = keyboard.nextLine();
		System.out.println("State:");
		String state = keyboard.nextLine();
		System.out.println("Zipcode:");
		String zipcode = keyboard.nextLine();
		return new Person(ID, first, last, mid, phone, gender, street, city, state, zipcode);
		
	}
}