package org.jsp.supllychainmanagment.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.entity.Orders;
import org.jsp.supllychainmanagment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

	@Autowired
	private OrderRepository orderRepository;
	
	public Orders createOrder(Orders orders) {
		return orderRepository.save(orders);
	}
	
	public Optional<Orders> getOrderById(int id){
		return orderRepository.findById(id);
	}
	
	public List<Orders> getAllOrders(){
		return orderRepository.findAll();
	}
	
	public List<Orders> getOrdersByCustomerId(int cid) {
		return orderRepository.getOrdersByCustomerId(cid);
	}
	
	public Optional<Orders> getOrdersByTrackingNumber(String tno) {
		return orderRepository.getOrdersByTrackingNumber(tno);
	}
	
	public Orders updateOrder(Orders orders){
		return orderRepository.save(orders);
	}
	
	public void deleteOrder(Orders order) {
		orderRepository.delete(order);
	}
}
