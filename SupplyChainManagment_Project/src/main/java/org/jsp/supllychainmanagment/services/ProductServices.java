package org.jsp.supllychainmanagment.services;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.ExceptionHandler.IdNotFoundException;
import org.jsp.supllychainmanagment.ExceptionHandler.NoRecordsFoundException;
import org.jsp.supllychainmanagment.dao.ProductDao;
import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product,int id){
		
		Product recivedProduct = productDao.addProduct(product,id);
		
		ResponseStructure<Product> response = new ResponseStructure<Product>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Product details are saved");
		response.setData(recivedProduct);
		
		return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Product>> getProductById(int id){
		Optional<Product> recievedProduct=productDao.getProductById(id);
		
		if(recievedProduct.isPresent()) {
			ResponseStructure<Product> response = new ResponseStructure<Product>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Product Details fetched");
            response.setData(recievedProduct.get());		
            
            return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		List<Product> list = productDao.getAllProduct();
		
		if(list.size()>0) {
			ResponseStructure<List<Product>> response = new ResponseStructure<List<Product>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("All Product Details fetched");
            response.setData(list);		
            
            return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordsFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> getProductBySupplierId(int id){
		List<Product> recivedProduct= productDao.getProductBySupplierId(id);
		
		if(recivedProduct.size()>0) {
			ResponseStructure<List<Product>> response= new ResponseStructure<List<Product>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Product details fetched by supplier id");
			response.setData(recivedProduct);
			
			return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product,int id){
        Product recivedProduct = productDao.addProduct(product,id);
		
		ResponseStructure<Product> response = new ResponseStructure<Product>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product details are updated");
		response.setData(recivedProduct);
		
		return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int id){
		Optional<Product> prod= productDao.getProductById(id);
		
		if(prod.isPresent()) {
		ResponseStructure<Product> response = new ResponseStructure<Product>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product details are deleted");
		response.setData(prod.get());
		
		productDao.deleteProduct(id);
		
		return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
	}
}
