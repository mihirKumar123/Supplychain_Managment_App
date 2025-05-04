package org.jsp.supllychainmanagment.controller;

import java.util.List;

import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Product;
import org.jsp.supllychainmanagment.services.ProductServices;
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
public class ProductController {
	
	@Autowired
	private ProductServices productServices;
	
	@PostMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product,@PathVariable int id){
		return productServices.addProduct(product,id);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable int id){
		return productServices.getProductById(id);
	}
	
	@GetMapping("/product")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		return productServices.getAllProduct();
	}
	
	@GetMapping("/product1/{id}")
	public ResponseEntity<ResponseStructure<List<Product>>> getProductBySupplierId(@PathVariable int id){
		return productServices.getProductBySupplierId(id);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product,@PathVariable int id){
		return productServices.updateProduct(product, id);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int id){
		return productServices.deleteProduct(id);
	}

}
