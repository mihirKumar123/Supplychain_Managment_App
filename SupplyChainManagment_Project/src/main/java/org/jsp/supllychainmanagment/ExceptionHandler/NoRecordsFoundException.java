package org.jsp.supllychainmanagment.ExceptionHandler;

public class NoRecordsFoundException extends RuntimeException {

	@Override
	public String getMessage() {
		return "NO ENTRIES FOUND";
	}
}
