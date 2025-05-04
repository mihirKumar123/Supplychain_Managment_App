package org.jsp.supllychainmanagment.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.entity.Supplier;
import org.jsp.supllychainmanagment.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierDao {

	@Autowired
	private SupplierRepository supplierRepository;
	
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public Optional<Supplier> getSupplierById(int id){
		return supplierRepository.findById(id);
	}
	
	public List<Supplier> getAllSupplier(){
		return supplierRepository.findAll();
	}
	
	public Supplier updateSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public void deleteSupplier(Supplier supplier) {
		supplierRepository.delete(supplier);
	}
}
