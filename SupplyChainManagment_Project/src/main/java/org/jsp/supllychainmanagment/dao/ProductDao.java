package org.jsp.supllychainmanagment.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.ExceptionHandler.IdNotFoundException;
import org.jsp.supllychainmanagment.entity.Product;
import org.jsp.supllychainmanagment.entity.Supplier;
import org.jsp.supllychainmanagment.repository.ProductRepository;
import org.jsp.supllychainmanagment.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	
	public Product addProduct(Product product,int id) {
		Optional<Supplier> recievedSupplier=supplierRepository.findById(id);
		
		if(recievedSupplier.isPresent()) {
			product.setSupplier(recievedSupplier.get());
			return productRepository.save(product);
		}else {
			throw new IdNotFoundException();
		}
		
	}
	
	public Optional<Product> getProductById(int id){
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public List<Product> getProductBySupplierId(int id){
		return productRepository.getProductBySupplierId(id);
	}
	
	public Product UpdateProduct(Product product,int id) {
		Optional<Supplier> recievedSupplier=supplierRepository.findById(id);
		
		if(recievedSupplier.isPresent()) {
			product.setSupplier(recievedSupplier.get());
			return productRepository.save(product);
		}else {
			throw new IdNotFoundException();
		}
	} 
	
	public void deleteProduct(int id) {
		Optional<Product> product= productRepository.findById(id);
		productRepository.delete(product.get());
	}
	
}
