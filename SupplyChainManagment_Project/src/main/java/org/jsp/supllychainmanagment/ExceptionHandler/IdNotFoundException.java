package org.jsp.supllychainmanagment.ExceptionHandler;

public class IdNotFoundException extends RuntimeException {
     
	@Override
	public String getMessage() {
		return "ID is not found in the Database";
	}
}
