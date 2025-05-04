package org.jsp.supllychainmanagment.ExceptionHandler;

public class AttributeNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
    	return "Give Attribute not Found";
    }
}
