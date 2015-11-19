package school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee extends Person {
	private GregorianCalendar dateHired;
	private GregorianCalendar dateOfBirth;
	private EmployeeType employeeTypeID;

	// constructor
	public Employee(Integer ID, String firstName, String lastName,
			Character midInitial, String phoneNumber, Character gender,
			String street, String city, String state, String zipcode,
			GregorianCalendar dateHired, GregorianCalendar dateOfBirth,
			String employeeTypeID) {
		super(ID, firstName, lastName, midInitial, phoneNumber, gender, street,
				city, state, zipcode);
		if (dateOfBirth == null
				|| dateHired == null
				|| employeeTypeID == null
				|| employeeTypeID.equals("")
				|| dateHired.get(Calendar.YEAR) < dateOfBirth
						.get(Calendar.YEAR) + 18)
			throw new InvalidDataException();
		this.dateHired = dateHired;
		this.dateOfBirth = dateOfBirth;
		this.employeeTypeID = validateEmployeeType(employeeTypeID);
	}

	// getters
	public GregorianCalendar getDateHired() {
		return new GregorianCalendar(dateHired.get(Calendar.YEAR),
				dateHired.get(Calendar.MONTH),
				dateHired.get(Calendar.DAY_OF_MONTH));
	}

	public GregorianCalendar getDateOfBirth() {
		return new GregorianCalendar(dateOfBirth.get(Calendar.YEAR),
				dateOfBirth.get(Calendar.MONTH),
				dateOfBirth.get(Calendar.DAY_OF_MONTH));
	}

	public EmployeeType getEmployeeTypeID() {
		return employeeTypeID;
	}

	// set employee type
	public void setEmployeeTypeID(String employeeTypeID) {
		if (employeeTypeID == null || employeeTypeID.equals(""))
			throw new InvalidDataException();
		this.employeeTypeID = validateEmployeeType(employeeTypeID);
	}

	// to string
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
		buffer.append(super.toString());
		buffer.append(" Date of Birth: "
				+ formatter.format(dateOfBirth.getTime()));
		buffer.append(" Date Hired: " + formatter.format(dateHired.getTime()));
		buffer.append(" Employee Type: " + employeeTypeID.toString());
		return buffer.toString();
	}

	// validate employee type
	private EmployeeType validateEmployeeType(String employeeTypeID) {
		for (EmployeeType e : EmployeeType.values()) {
			if (employeeTypeID.equalsIgnoreCase(e.toString()))
				return e;
		}
		throw new InvalidDataException();
	}
}
