package org.jsp.supllychainmanagment.ExceptionHandler;

import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>>  handleIdNotFoundException(IdNotFoundException exception){
		ResponseStructure<String> response= new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Inserted ID value is not found");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoRecordsFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNoRecordsFoundXception(NoRecordsFoundException exception){
		ResponseStructure<String> responseStructure= new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("NO ENTRIES FOUND");
		responseStructure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<ResponseStructure<String>> handleAttributeNotFoundException(AttributeNotFoundException exception){
		ResponseStructure<String> response = new ResponseStructure<String>();
		
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Inserted Attribute Not Found");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
}
