package school;

public enum Major {
	ACCT("Accounting"), ART("Art"), BIOL("Biology"), CHEM("Chemistry"), CPSC("Computer Science"), ECON("Economics"), EDUC("Education"), 
	ENGL("English"), ENGR("ENGINEERING"), FRCH("French"), GEOG("Geology"), GERM("German"), GREE("Greek"), HIST("History"), MATH("Math"),
	MUSC("MUSIC"), 	NURS("nursing"), PHIL("Philosophy"), PE("Physical Ed"), PHYS("Physics"), POLS("Political Science"), PSYC("Psychology"), 
	RELI("Religion"), SOCI("Sociology"), SPEE("Speech"), UDCD("Undecided"); 

private String description;

private Major(String description){
	this.description = description;
}

public String getDescription(){
	return this.description;
}
}
