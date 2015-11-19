package school;

public class Person implements Comparable<Person> {
	private Integer ID;
	private String firstName;
	private String lastName;
	private Character midInitial;
	private Address address;
	private String phoneNumber;
	private Character gender;

	// no middle initial
	public Person(Integer ID, String firstName, String lastName,
			String phoneNumber, Character gender, String street, String city,
			String state, String zipcode) {
		this(ID, firstName, lastName, null, phoneNumber, gender, street, city,
				state, zipcode);
	}

	// no phone number
	public Person(Integer ID, String firstName, String lastName,
			Character midInitial, Character gender, String street, String city,
			String state, String zipcode) {
		this(ID, firstName, lastName, midInitial, null, gender, street, city,
				state, zipcode);
	}

	// no phone number and no middle initial
	public Person(Integer ID, String firstName, String lastName,
			Character gender, String street, String city, String state,
			String zipcode) {
		this(ID, firstName, lastName, null, null, gender, street, city, state,
				zipcode);
	}

	// standard Person constructor
	public Person(Integer ID, String firstName, String lastName,
			Character midInitial, String phoneNumber, Character gender,
			String street, String city, String state, String zipcode) {
		if (ID == null || firstName == null || firstName.equals("")
				|| lastName == null || lastName.equals("") || gender == null || !isValidNumber(phoneNumber))
			throw new InvalidDataException();

		this.ID = ID;
		this.address = new Address(street, city, state, zipcode);
		this.firstName = firstName;
		this.lastName = lastName;
		this.midInitial = midInitial;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	// setters
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals(""))
			throw new InvalidDataException();
		this.lastName = lastName;
	}

	public void setAddress(Address newAddress) {
		this.address = newAddress;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (!isValidNumber(phoneNumber))
			throw new InvalidDataException();
		this.phoneNumber = phoneNumber;
	}

	// gettters
	public Integer getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Character getMidInitial() {
		return midInitial;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Address getAddress() {
		return new Address(address.getStreet(), address.getCity(),
				address.getState(), address.getZipcode());
	}

	public Character getGender() {
		return gender;
	}

	// compareTo -used to sort Teachers and Students by ID
	public int compareTo(Person p) {
		return this.ID.compareTo(p.getID());
			}

	// equals
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof Person) {
			Person other = (Person) o;
			return (this.ID == other.ID);
		} else
			return false;
	}

	// to string
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\nID: " + this.ID);
		buffer.append(" " + lastName + ", " + firstName);
		if (midInitial != null)
			buffer.append(" " + midInitial);
		buffer.append(address.toString());
		if (phoneNumber == null || phoneNumber.equals(""))
			buffer.append(" Phone Number: N/A ");
		else
			buffer.append(" Phone Number: " + phoneNumber);
		buffer.append(" Gender: " + gender);
		return buffer.toString();
	}
	//validate the phone number
	private boolean isValidNumber(String phoneNumber){
		if(phoneNumber == null || phoneNumber.equals("") || phoneNumber.equals("N/A"))
			return true;
		else if(phoneNumber.matches("\\d{10}")){
			return true;
		}
		return false;
			}
}