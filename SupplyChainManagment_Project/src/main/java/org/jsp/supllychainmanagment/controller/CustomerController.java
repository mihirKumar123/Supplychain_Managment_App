package org.jsp.supllychainmanagment.controller;

import java.util.List;

import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Customer;
import org.jsp.supllychainmanagment.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerServices customerServices;
	
	@PostMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> addCustomer(@RequestBody Customer customer){
		return customerServices.addCustomer(customer);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable int id){
		return customerServices.getCustomerById(id);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
		return customerServices.getAllCustomer();
	}
	
	@PutMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer){
		return customerServices.updateCustomer(customer);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable int id){
		return customerServices.deleteCustomer(id);
	}

}
