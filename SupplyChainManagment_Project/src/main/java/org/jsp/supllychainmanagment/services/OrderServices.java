package org.jsp.supllychainmanagment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.ExceptionHandler.AttributeNotFoundException;
import org.jsp.supllychainmanagment.ExceptionHandler.IdNotFoundException;
import org.jsp.supllychainmanagment.ExceptionHandler.NoRecordsFoundException;
import org.jsp.supllychainmanagment.dao.CustomerDao;
import org.jsp.supllychainmanagment.dao.OrderDao;
import org.jsp.supllychainmanagment.dao.ProductDao;
import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Customer;
import org.jsp.supllychainmanagment.entity.Orders;
import org.jsp.supllychainmanagment.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServices {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	public ResponseEntity<ResponseStructure<Orders>> createOrder(Orders orders,List<Integer> products,int cid) {
		Optional<Customer> recivedCustomer= customerDao.getCustomerById(cid);
		
		orders.setCustomer(recivedCustomer.get());
		
		List<Product> lProducts= new ArrayList<Product>();
		
		for(int i: products) {
			Optional<Product> product=productDao.getProductById(i);
			if(product.isPresent()) {
				lProducts.add(product.get());
			}else {
				throw new IdNotFoundException();
			}
		}
		
		orders.setProduct(lProducts);
		Orders recievedOrder= orderDao.createOrder(orders);
		
		
		
		ResponseStructure<Orders> response= new ResponseStructure<Orders>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Order Details saved");
		response.setData(recievedOrder);
		
		return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Orders>> getProductById(int id){
		Optional<Orders> recivedOrders=orderDao.getOrderById(id);
		
		if(recivedOrders.isPresent()) {
			ResponseStructure<Orders> response= new ResponseStructure<Orders>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Order details found by Id");
			response.setData(recivedOrders.get());
			
			return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders(){
		List<Orders> list= orderDao.getAllOrders();
		
		if(list.size()>0) {
			ResponseStructure<List<Orders>> response = new ResponseStructure<List<Orders>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Fetched All Orders");
			response.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<Orders>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordsFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByCustomerId(int cid){
		List<Orders> recivedOrder= orderDao.getOrdersByCustomerId(cid);
		
		if(recivedOrder.size()>0) {
			ResponseStructure<List<Orders>> response= new ResponseStructure<List<Orders>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Order Details found by customer Id");
			response.setData(recivedOrder);
			
			return new ResponseEntity<ResponseStructure<List<Orders>>>(response, HttpStatus.FOUND);
		}else {
			throw new IdNotFoundException();
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(String tno){
		Optional<Orders> recivedOrder = orderDao.getOrdersByTrackingNumber(tno);
		
		if(recivedOrder.isPresent()) {
			ResponseStructure<Orders> response= new ResponseStructure<Orders>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Order Details found by customer Id");
			response.setData(recivedOrder.get());
			
			return new ResponseEntity<ResponseStructure<Orders>>(response, HttpStatus.FOUND);
		}else {
			throw new AttributeNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Orders>> updateOrder(Orders orders,List<Integer> products) {
		List<Product> lProducts= new ArrayList<Product>();
		
		for(int i=0;i<products.size();i++) {
			Optional<Product> product= productDao.getProductById(products.get(i));
			
			if(product.isPresent()) {
				lProducts.add(product.get());
			}else {
				throw new IdNotFoundException();
			}
		}
		
		orders.setProduct(lProducts);
		Orders recievedOrder= orderDao.updateOrder(orders);
		
		ResponseStructure<Orders> response= new ResponseStructure<Orders>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Order Details updated");
		response.setData(recievedOrder);
		
		return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(int id){
		Optional<Orders> recievedOrder= orderDao.getOrderById(id);
		
		if(recievedOrder.isPresent()) {
		ResponseStructure<Orders> response= new ResponseStructure<Orders>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Order Details deleted");
		response.setData(recievedOrder.get());
		
		orderDao.deleteOrder(recievedOrder.get());
		
		return new  ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.OK);
		
		}else {
			throw new IdNotFoundException();
		}
	}
	
	
	
	
}
