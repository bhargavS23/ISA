package com.zettamine.isa.validation;

public class ValidateCredentials {
	
	
	public boolean validateName(String name) {
		
		if(name.length() > 2) {
			return name.matches("[a-z A-Z]+");
		}
		else return false;
	}
	
	public boolean validateEmail(String email) {
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
		
		if(email.length() > 5 ) {
			return email.matches(emailRegex);
		}
		return false;
	}
	
	public boolean validatePhoneNum(String phNo) {
		
		if(phNo.length() >= 10 && phNo.length() <= 13) {
			return phNo.matches("^\\+?[0-9-()\\s]+$");
		}
		return false;
	}

}
