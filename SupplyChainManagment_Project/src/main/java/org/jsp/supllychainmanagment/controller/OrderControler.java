package org.jsp.supllychainmanagment.controller;

import java.util.List;

import org.jsp.supllychainmanagment.dto.ResponseStructure;
import org.jsp.supllychainmanagment.entity.Orders;
import org.jsp.supllychainmanagment.services.OrderServices;
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
public class OrderControler {

	@Autowired
	private OrderServices orderServices;
	
	@PostMapping("/orders/{products}/{cid}")
	public ResponseEntity<ResponseStructure<Orders>> createOrder(@RequestBody Orders order,@PathVariable List<Integer> products,@PathVariable int cid){
		return orderServices.createOrder(order,products,cid);
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderById(@PathVariable int id){
		return orderServices.getProductById(id);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders(){
		return orderServices.getAllOrders();
	}
	
	@GetMapping("/orders1/{cid}")
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByCustomerId(@PathVariable int cid){
		return orderServices.getOrderByCustomerId(cid);
	}
	
	@GetMapping("/orders2/{tno}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(@PathVariable String tno){
		return orderServices.getOrderByTrackingNumber(tno);
	}
	
	@PutMapping("/order/{products}")
	public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody Orders order,@PathVariable List<Integer> products){
		return orderServices.updateOrder(order,products);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<ResponseStructure<Orders>> deleteOrders(@PathVariable int id){
		return orderServices.deleteOrder(id);
	}
}
