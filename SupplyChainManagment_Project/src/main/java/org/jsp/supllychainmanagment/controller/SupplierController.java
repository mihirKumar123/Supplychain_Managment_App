package org.jsp.supllychainmanagment.controller;

import java.util.List;

import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Supplier;
import org.jsp.supllychainmanagment.services.SupplierServices;
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

@RestController
@RequestMapping("/api")
public class SupplierController {

	@Autowired
	private SupplierServices supplierServices;
	
	@PostMapping("/supplier")
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(@RequestBody Supplier supplier){
		return supplierServices.saveSupplier(supplier);
	}
	
	@GetMapping("/supplier/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(@PathVariable int id){
		return supplierServices.getSupplierById(id);
	}
	
	@GetMapping("/supplier")
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier(){
		return supplierServices.getAllSupplier();
	}
	
	@PutMapping("/supplier")
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@RequestBody Supplier supplier){
		return supplierServices.updateSupplier(supplier);
	}
	
	@DeleteMapping("/supplier/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplier(@PathVariable int id){
		return supplierServices.deleteSupplier(id);
	}
}
