package org.jsp.supllychainmanagment.services;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.ExceptionHandler.IdNotFoundException;
import org.jsp.supllychainmanagment.ExceptionHandler.NoRecordsFoundException;
import org.jsp.supllychainmanagment.dao.SupplierDao;
import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupplierServices {

	@Autowired
	private SupplierDao supplierDao;
	
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier){
		Supplier recievedSupplier=supplierDao.saveSupplier(supplier);
		
		ResponseStructure<Supplier> structure= new ResponseStructure<Supplier>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Supplier Record created");
		structure.setData(recievedSupplier);
		
		return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(int id){
		Optional<Supplier> recievedSupplier= supplierDao.getSupplierById(id);
		
		if(recievedSupplier.isPresent()) {
			ResponseStructure<Supplier> structure= new ResponseStructure<Supplier>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Supplier Record fetched");
			structure.setData(recievedSupplier.get());
			
			return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.OK);
		}else {
		    throw new IdNotFoundException();
		}
	}
	
	  public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier(){
		List<Supplier> list=supplierDao.getAllSupplier();
		
		if(list.size()>0) {
			ResponseStructure<List<Supplier>> structure= new ResponseStructure<List<Supplier>>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Supplier Records fetched");
			structure.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<Supplier>>>(structure, HttpStatus.OK);
		}else {
			throw new NoRecordsFoundException();
		}
	}
	  
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier){
        Supplier recievedSupplier=supplierDao.updateSupplier(supplier);
		
		ResponseStructure<Supplier> structure= new ResponseStructure<Supplier>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Supplier Record Updated");
		structure.setData(recievedSupplier);
		
		return new ResponseEntity<ResponseStructure<Supplier>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplier(int id){
		Optional<Supplier> optional = supplierDao.getSupplierById(id);
		
		if(optional.isPresent()) {
			 ResponseStructure<Supplier> response= new ResponseStructure<Supplier>();
			 response.setStatusCode(HttpStatus.OK.value());
			 response.setMessage("Requested Record Deleted");
			 response.setData(optional.get());
			 
			 supplierDao.deleteSupplier(optional.get());
			 
			 return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
		
		
	}
	  
}

































