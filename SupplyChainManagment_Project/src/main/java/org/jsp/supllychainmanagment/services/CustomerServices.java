package org.jsp.supllychainmanagment.services;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.ExceptionHandler.IdNotFoundException;
import org.jsp.supllychainmanagment.ExceptionHandler.NoRecordsFoundException;
import org.jsp.supllychainmanagment.dao.CustomerDao;
import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {

	@Autowired
	private CustomerDao customerDao;
	
	
	
	public ResponseEntity<ResponseStructure<Customer>> addCustomer(Customer customer){
		
		Customer recivedCustomer=customerDao.addCustomer(customer);
		
		ResponseStructure<Customer> response= new ResponseStructure<Customer>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("New Customer Added");
		response.setData(recivedCustomer);
		
		return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id){
		Optional<Customer> optional = customerDao.getCustomerById(id);
		
		if(optional.isPresent()) {
			ResponseStructure<Customer> response= new ResponseStructure<Customer>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Fetched Customer details of the given Id");
			response.setData(optional.get());
            
			return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
		List<Customer> list = customerDao.getAllCustomer();
		if(list.size()>0) {
		ResponseStructure<List<Customer>> response= new ResponseStructure<List<Customer>>();
		response.setStatusCode(HttpStatus.FOUND.value());
		response.setMessage("Fetched All The Records");
		response.setData(list);
		
		return new ResponseEntity<ResponseStructure<List<Customer>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordsFoundException();
		}
	}
	
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer){
		
		
		Customer recivedCustomer=customerDao.addCustomer(customer);
		
		ResponseStructure<Customer> response= new ResponseStructure<Customer>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("New Customer Added");
		response.setData(recivedCustomer);
		
		return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int id){
		Optional<Customer> optional = customerDao.getCustomerById(id);
		
		if(optional.isPresent()) {
			ResponseStructure<Customer> response= new ResponseStructure<Customer>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Deleted Customer details of the given Id");
			response.setData(optional.get());
            
			customerDao.deleteCustomer(optional.get());
			return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
	
}
