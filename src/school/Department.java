package school;

public class Department {
	private String departmentID;
	private String departmentName;
	private String location;
	private String phoneNumber;
	private String faxNumber;
	private String chairPerson;

	// Department Constructors
	public Department(String deptID) {
		this(deptID, null, null, null, null, null);
	}

	public Department(String deptID, String departmentName) {
		this(deptID, departmentName, null, null, null, null);
	}

	public Department(String deptID, String departmentName, String location) {
		this(deptID, departmentName, location, null, null, null);
	}

	public Department(String deptID, String departmentName, String location,
			String phoneNumber) {
		this(deptID, departmentName, location, phoneNumber, null, null);
	}

	public Department(String deptID, String departmentName, String location,
			String phoneNumber, String faxNumber) {
		this(deptID, departmentName, location, phoneNumber, faxNumber, null);
	}

	public Department(String deptID, String departmentName, String location,
			String phoneNumber, String faxNumber, String chairPerson) {
		if (deptID == null || deptID.equals("")
				|| !isValidNumber(phoneNumber) || !isValidNumber(faxNumber))
				throw new InvalidDataException();
		this.departmentID = deptID;
		this.departmentName = departmentName;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.chairPerson = chairPerson;
	}

	// setters
	public void setLocation(String location) {
		if (location == null || location.equals(""))
			throw new InvalidDataException();
		this.location = location;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (!isValidNumber(phoneNumber))
			throw new InvalidDataException();
		this.phoneNumber = phoneNumber;
	}

	public void setFaxNumber(String faxNumber) {
		if (!isValidNumber(faxNumber))
			throw new InvalidDataException();
		this.faxNumber = faxNumber;
	}

	public void setChair(String chairPerson) {
		if (chairPerson == null || chairPerson.equals(""))
			throw new InvalidDataException();
		this.chairPerson = chairPerson;
	}

	// getters
	public String getDepartmentID() {
		return departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getLocation() {
		return location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public String getChair() {
		return chairPerson;
	}

	// to string
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("\nDepartment ID: " + departmentID);
		buffer.append(" DepartmentName: " + departmentName);
		buffer.append(" Location: " + location);
		buffer.append(" Phone Number: " + phoneNumber);
		buffer.append(" Fax Number: " + faxNumber);
		buffer.append(" Chair Person: " + chairPerson);
		return buffer.toString();
	}

	// compare to
	public int compareTo(Department aDepartment) {
		return departmentID.compareTo(aDepartment.departmentID);
	}

	// equals
	public boolean equals(Object o) {
		if (o == null)
			return false;

		else {
			if (o instanceof Department) {
				Department other = (Department) o;
				return departmentID.compareTo(other.getDepartmentID()) == 0;
			} else
				return false;
		}
	}
	//validate the phone number and fax number
		private boolean isValidNumber(String number){
			if(number == null || number.equals(""))
				return true;
			else if(number.matches("\\d{10}")){
				return true;
			}
			return false;
				}
		}