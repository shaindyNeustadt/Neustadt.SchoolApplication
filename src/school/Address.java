package school;

public class Address {
	private String street;
	private String city;
	private State state;
	private String zipcode;

	// Address constructor without street and zipcode values
	public Address(String city, String state) {
		this(null, city, state, null);
	}

	// standard Address constructor
	public Address(String street, String city, String state, String zipcode) {
		if (city == null
				|| city.equals("")
				|| state == null
				|| state.equals("")
				|| !isValidZip(zipcode))
			throw new InvalidDataException();

		this.street = street;
		this.city = city;
		this.state = getStateCode(state);
		this.zipcode = zipcode;

	}

	// verify state is a valid entry, if not valid throw InvalidDataException
	private static State getStateCode(String state) {
		for (State s : State.values()) {
			state = state.trim();
			if (state.equalsIgnoreCase(s.name())
					|| state.equalsIgnoreCase(s.getStateCode()))
				return s;
		}
		throw new InvalidDataException();
	}

	// setters
	public void setStreet(String street) {
		if (street == null || street.equals(""))
			throw new InvalidDataException();
		this.street = street;
	}

	public void setCity(String city) {
		if (city == null || city.equals(""))
			throw new InvalidDataException();
		this.city = city;
	}

	public void setState(String state) {
		State stateCode = getStateCode(state);
		if (stateCode == null)
			throw new InvalidDataException();
		this.state = stateCode;
	}

	public void setZipcode(String zipcode) {
		if (!isValidZip(zipcode))
			throw new InvalidDataException();
		this.zipcode = zipcode;
	}

	// getters
	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state.toString();
	}

	public String getZipcode() {
		return zipcode;
	}
	private boolean isValidZip(String zipcode){
		//the student.txt file does not contain a zipcode value
		if(zipcode == null || zipcode.equals(""))
			return true;
		else if(zipcode.matches("\\d{5}") || zipcode.matches("\\d{9}")){
			return true;
		}
		return false;
			}
	// to string
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (street == null || zipcode == null)
			buffer.append("\nAddress: " + city + ", " + state);
		else {
			buffer.append("\nAddress: " + street);
			buffer.append(city + ", " + state + " " + zipcode);
		}
		return buffer.toString();

	}
}
